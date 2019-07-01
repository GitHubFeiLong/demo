package ssm.redis.work1;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @ClassName Jedis
 * @Description TODO
 * @Author cfl
 * @Date 2019/6/27 10:43
 * @Version 1.0
 */
public class Jedis {
	/**
	 *
		 Redis通信协议：RESP
		 *3 （*3：3组数据）
		 $3 （$3：SET 3个字符）
		 SET
		 $11 （$11：lisonLength 11个字符）
		 lisonLength
		 $1  （$1：3 1个字符）
		 3
 	 *  @Param []
	 * @Return java.lang.String
	 */
	public static String set(Socket socket, String key, String value) throws IOException {
		
		StringBuffer str = new StringBuffer();
		str.append("*3").append("\r\n");
		str.append("$3").append("\r\n");
		str.append("set").append("\r\n");
		str.append("$").append(key.getBytes().length).append("\r\n");
		str.append(key).append("\r\n");
		str.append("$").append(value.getBytes().length).append("\r\n");
		str.append(value).append("\r\n");
		socket.getOutputStream().write(str.toString().getBytes());
		byte[] response = new byte[2048];
		socket.getInputStream().read(response);
		return new String(response);
	}
	public static String get(Socket socket, String key) throws IOException {
		
		StringBuffer str = new StringBuffer();
		str.append("*2").append("\r\n");
		str.append("$3").append("\r\n");
		str.append("get").append("\r\n");
		str.append("$").append(key.getBytes().length).append("\r\n");
		str.append(key).append("\r\n");
		
		socket.getOutputStream().write(str.toString().getBytes());
		byte[] response = new byte[2048];
		socket.getInputStream().read(response);
		return new String(response);
	}
	
	public static void main(String[] args){
		try (Socket socket = new Socket("127.0.0.1", 6379)) {
			set(socket,"length", "20");
			System.out.println(get(socket, "length"));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
