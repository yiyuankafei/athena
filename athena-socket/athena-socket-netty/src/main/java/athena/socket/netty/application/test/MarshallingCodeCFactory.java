package athena.socket.netty.application.test;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

public class MarshallingCodeCFactory {
	
	public static MarshallingDecoder buildMarshallingDecoder() {
		//首先通过Marshalling工具的方法获取Marshalling实例对象， serial标识创建的是JAVA序列化工厂
		final MarshallerFactory f = Marshalling.getProvidedMarshallerFactory("serial");
		//配置版本号5
		final MarshallingConfiguration c = new MarshallingConfiguration();
		c.setVersion(5);
		//创建provider
		UnmarshallerProvider p = new DefaultUnmarshallerProvider(f, c);
		//构建netty的MarshallingDecoder对象，两个参数分别为provider和单个消息序列化后的最大长度
		MarshallingDecoder d = new MarshallingDecoder(p, 1024 * 2014 * 1);
		return d;
	}
	
	public static MarshallingEncoder buildMarshallingEncoder() {
		final MarshallerFactory f = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration c = new MarshallingConfiguration();
		c.setVersion(5);
		MarshallerProvider p = new DefaultMarshallerProvider(f, c);
		//构建netty的MarshallingEncoder对象，用于实现序列化接口的POJO对象序列化为二进制数组
		MarshallingEncoder e = new MarshallingEncoder(p);
		return e;
	}

}
