package com.alex.j2se.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * 测试文件加锁
 * @author alex
 *
 */
public class FileLocking {
	
	static final int LENGTH = 0x8FFFFFF; // 128M
	
	static FileChannel fc;

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		lockingMappedFile();
	}
	
	/**
	 * 对文件通道（FileChannel）进行加锁和解锁
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void fileLocking() throws IOException, InterruptedException {
		FileOutputStream fos = new FileOutputStream("d:/nio.txt");
		FileLock fl = fos.getChannel().lock();
		if(fl != null) {
			System.out.println("Locked file");
			TimeUnit.MILLISECONDS.sleep(100);
			fl.release();
			System.out.println("Realse lock");
		}
		fos.close();
	}
	
	/**
	 * 对fileChanel进行区域加锁，并使用多线程进行写入操作
	 * @throws IOException
	 */
	public static void lockingMappedFile() throws IOException {
		fc = new RandomAccessFile("d:/temp1.tmp", "rw").getChannel();
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
		for(int i = 0; i < LENGTH; ++i) {
			mbb.put((byte)'x');
		}
		new LockAndModify(mbb, 0, LENGTH/3);
		new LockAndModify(mbb, LENGTH/2, LENGTH/2 + LENGTH/4);
	}
	
	private static class LockAndModify extends Thread {
		private ByteBuffer bb;
		private int start,end;
		LockAndModify(ByteBuffer bb,int start,int end) {
			this.start = start;
			this.end = end;
			bb.limit(end);
			bb.position(start);
			this.bb = bb.slice();
			start();
		}
		
		public void run() {
			try {
				FileLock fLock = fc.lock(start, end, false);
				System.out.println("Locked: " + start + " to " + end);
				while(bb.position() < bb.limit()-1) {
					bb.put((byte)(bb.get()+1));
				}
				fLock.release();
				System.out.println("Release: " + start + " to " + end);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} 
		}
	}

}
