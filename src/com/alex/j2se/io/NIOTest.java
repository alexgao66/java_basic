package com.alex.j2se.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		copyTest();
	}
	
	/**
	 * 使用FileChannel ByteBuffer进行文件简单读写操作
	 * @throws IOException
	 */
	public static void testFileChannel() throws IOException {
		// 写入字符串
		FileChannel  fc = new FileOutputStream("d:/nio.txt").getChannel();
		fc.write(ByteBuffer.wrap("alex gao".getBytes()));
		fc.close();
		
		// 在文件末尾写入字符串
		fc = new RandomAccessFile("d:/nio.txt", "rw").getChannel();
		// 移动到文件末尾
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap(" fighting!".getBytes()));
		fc.close();
		
		// 输出文件
		fc = new FileInputStream("d:/nio.txt").getChannel();
		// 使用1K的缓存
		ByteBuffer bb = ByteBuffer.allocate(1024);
		fc.read(bb);
		// 做写入准备
		bb.flip();
		while(bb.hasRemaining()) {
			System.out.print((char)bb.get());
		}
	}
	
	/**
	 * 使用FileChannel的读写进行文件的拷贝
	 * @throws IOException
	 */
	public static void channelCopy() throws IOException {
		FileChannel in = new FileInputStream("d:/DirList.java").getChannel(),
				out = new FileOutputStream("d:/DirList.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(1024);
		while((in.read(buff)) != -1) {
			buff.flip();
			out.write(buff);
			// 清空，为读入做准备
			buff.clear();
		}
		out.close();
	}
	
	/**
	 * 使用FileChannel对FileChannel进行复制拷贝
	 * 并对比与使用BufferedInputStream之间的复制性能差别
	 * @throws IOException
	 */
	public static void transferTo() throws IOException {
		long start1 = System.currentTimeMillis();
		FileChannel in = new RandomAccessFile("E:\\work\\sino\\comp\\svn\\module\\log\\log.rar","r").getChannel(),
				out = new RandomAccessFile("d:/log1.rar","rw").getChannel();
		// 或者使用out.transferOut(in,start,end)
		in.transferTo(0, in.size(), out);
		in.close();
		out.close();
		System.out.println(System.currentTimeMillis()-start1);
		
		long start2 = System.currentTimeMillis();
		BufferedInputStream streamIn = new BufferedInputStream(new FileInputStream("E:\\work\\sino\\comp\\svn\\module\\log\\log.rar"));
		BufferedOutputStream streamOut = new BufferedOutputStream(new FileOutputStream("d:/log2.rar"));
		// 通过调整缓存大小，传统的io复制比使用nio的通道传输还快
		byte[] byteArray = new byte[20480];
		while(streamIn.read(byteArray) != -1) {
			streamOut.write(byteArray);
		}
		streamIn.close();
		streamOut.close();
		System.out.println(System.currentTimeMillis()-start2);
	}
	
	/**
	 * 测试使用mappedByteBuffer和stream文件拷贝
	 */
	public static void copyTest() {
		Tester[] testers = {
				// 映射整个文件
				new Tester("Mapping copy") {
					@Override
					public void test() throws IOException {
						FileChannel fcIn = new RandomAccessFile("E:\\work\\sino\\comp\\svn\\module\\log\\log.rar", 
								"r").getChannel();
						MappedByteBuffer in = fcIn.map(FileChannel.MapMode.READ_ONLY, 0, fcIn.size());
						FileChannel fcOut = new RandomAccessFile("d:/log1.rar", 
								"rw").getChannel();
						MappedByteBuffer out = fcOut.map(FileChannel.MapMode.READ_WRITE, 0, fcIn.size());
						out.put(in);
						fcIn.close();
						fcOut.close();
					}
				},
				
				// 映射指定指定大小
				new Tester("Mapping buffer size copy") {
					@Override
					public void test() throws IOException {
						FileChannel fcIn = new RandomAccessFile("E:\\work\\sino\\comp\\svn\\module\\log\\log.rar", 
								"r").getChannel();
						long size = 1024*1024*100;
						FileChannel fcOut = new RandomAccessFile("d:/log3.rar", 
								"rw").getChannel();
						long fileSize = fcIn.size();
						long c = fileSize/size;
						MappedByteBuffer in,out;
						for(long i = 0; i < c; ++i) {
							in = fcIn.map(FileChannel.MapMode.READ_ONLY, size*i, size*(i+1));
							out = fcOut.map(FileChannel.MapMode.READ_WRITE, size*i, size*(i+1));
							out.put(in);
						}
						in = fcIn.map(FileChannel.MapMode.READ_ONLY, size*c, fileSize-size*c);
						out = fcOut.map(FileChannel.MapMode.READ_WRITE, size*c, fileSize-size*c);
						out.put(in);
						fcIn.close();
						fcOut.close();
					}
				},
				
				// 使用buffered stream
				new Tester("Stream copy") {
					@Override
					public void test() throws IOException {
						BufferedInputStream streamIn = new BufferedInputStream(new FileInputStream("E:\\work\\sino\\comp\\svn\\module\\log\\log.rar"));
						BufferedOutputStream streamOut = new BufferedOutputStream(new FileOutputStream("d:/log2.rar"));
						// 通过调整缓存大小，传统的io复制比使用nio的通道传输还快
						byte[] byteArray = new byte[1024*1024*2];
						int i = 0;
						while((i = streamIn.read(byteArray))!= -1) {
							streamOut.write(byteArray,0,i);
						}
						streamIn.close();
						streamOut.close();
					}
				}
		};
		for(Tester tester : testers) {
			tester.runTest();
		}
	}
	
	/**
	 * (视图缓冲器) 对视图的修改，bytebuffer也会立刻修改
	 */
	public static void intBufferDemo() {
		ByteBuffer bb = ByteBuffer.allocate(1024);
		IntBuffer ib = bb.asIntBuffer();
		// 存储一个int数组
		ib.put(new int[]{1,2,3,4,5,6});
		// 通过下标进行读写
		System.out.println(ib.get(3));
		ib.put(3, 7);
		// 重置读操作所在位置
		ib.flip();
		while(ib.hasRemaining()) {
			int i = ib.get();
			System.out.println(i);
		} 
	}
	
	/**
	 * 使用MappedByteBuffer(文件内存映射)进行读写
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void mappedByteBufferTest() throws FileNotFoundException, IOException {
		File file = new File("d:/nio.txt");
		MappedByteBuffer mbb = new RandomAccessFile(file, "rw").getChannel()
				.map(FileChannel.MapMode.READ_WRITE, file.length(), file.length());
		mbb.put(" for your dream!".getBytes());
		System.out.println("Finish write.");
		for(int i = 0,length = (int)file.length(); i < length; ++i) {
			System.out.print((char)mbb.get(i));
		}
	}
	
	/**
	 * 使用匿名内部类测试stream和byteBuffer的读写效率差别
	 */
	public static void mappedIO() {
		final int numOfInts = 4000000;
		final int numOfUbuffInts = 200000;
		Tester[] tester = {
			new Tester("Stream writer") {
				@Override
				public void test() throws IOException {
					DataOutputStream dos = new DataOutputStream(
							new BufferedOutputStream(new FileOutputStream("d:/temp.tmp")));
					for(int i = 0; i < numOfInts; ++i) {
						dos.writeInt(i);
					}
					dos.close();
				}
				
			},
			
			new Tester("Mapped writer") {
				@Override
				public void test() throws IOException {
					FileChannel fc = new RandomAccessFile("d:/temp.tmp", "rw").getChannel();
					MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());
					IntBuffer ib = mbb.asIntBuffer();
					for(int i = 0; i < numOfInts; ++i) {
						ib.put(i);
					}
					fc.close();
				}
				
			},
			
			new Tester("Stream reader") {
				@Override
				public void test() throws IOException {
					DataInputStream dis = new DataInputStream(
								new BufferedInputStream(new FileInputStream("d:/temp.tmp")));
					for(int i = 0; i < numOfInts; ++i) {
						dis.readInt();
					}
					dis.close();
				}
			},
			
			new Tester("Mapped reader") {
				@Override
				public void test() throws IOException {
					FileChannel fc = new RandomAccessFile("d:/temp.tmp", "r").getChannel();
					IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
					while(ib.hasRemaining()) {
						ib.get();
					}
					fc.close();
				}
			},
			
			new Tester("Stream writer/reader") {
				@Override
				public void test() throws IOException {
					RandomAccessFile raf = new RandomAccessFile("d:/temp.tmp", "rw");
					raf.writeInt(1);
					for(int i = 0; i < numOfUbuffInts; ++i) {
						raf.seek(raf.length()-4);
						raf.write(raf.readInt());
					}
					raf.close();
				}
			},
			
			new Tester("Mapped writer/reader") {
				@Override
				public void test() throws IOException {
					FileChannel fc = new RandomAccessFile("d:/temp.tmp", "rw").getChannel();
					IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
					ib.put(0);
					for(int i = 1; i < numOfUbuffInts; ++i) {
						ib.put(ib.get(i-1));
					}
					fc.close();
				}
			}
		};
		
		for(Tester test : tester) {
			test.runTest();
		}
	}
	
	/**
	 * 用于测试input/outputstream和mappedByteStream读写的静态内部抽象类。
	 * 使用模版方法模型
	 * @author alex
	 *
	 */
	public abstract static class Tester{
		private String name;
		public Tester(String name) {
			this.name = name;
		}
		public abstract void test() throws IOException; 
		
		public void runTest() {
			System.out.print(name + ":");
			try {
				long start = System.currentTimeMillis();
				test();
				System.out.println(System.currentTimeMillis()-start);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
