namespace java com.alex.thrift.hello.server


service HelloService {
    string say(1:string name)
}