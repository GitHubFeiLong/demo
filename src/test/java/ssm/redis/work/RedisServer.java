package ssm.redis.work;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName RedisServer
 * @Description TODO
 * @Author cfl
 * @Date 2019/6/26 13:10
 * @Version 1.0
 */
public class RedisServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(6379);
			Socket socket = serverSocket.accept();
			byte[] result = new byte[2048];
			socket.getInputStream().read(result);
			System.out.println(new String(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
