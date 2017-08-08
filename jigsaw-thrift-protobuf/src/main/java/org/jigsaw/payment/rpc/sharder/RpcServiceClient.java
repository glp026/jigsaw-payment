package org.jigsaw.payment.rpc.sharder;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TTransport;
import org.jigsaw.payment.rpc.BaseService;
import org.jigsaw.payment.rpc.SystemException;

import com.google.protobuf.Message;

/**
 * 调用RPC的客户端
 * @author shamphone@gmail.com
 */
public class RpcServiceClient {
	/**
	 * Transport 连接池
	 */
	protected TransportManager transportManager;
	public void setTransportManager( TransportManager transportManager){
		this.transportManager = transportManager;
		
	}



	/**
	 * 调用RPC的通用方法。这里的实现方法是通过对BaseService的execute调用进行转换，这使得其他的RPC调用不需要再定义thrift 方法，仅需定义输入输出的protobuf message。
	 * @param serviceName：RPC服务方法名
	 * @param request： 请求的message
	 * @param responseClass: 返回的response类型
	 * @return rpc调用结果
	 * @throws TException 调用中发生的错误
	 */
	public <Response extends Message, Request extends Message> Response execute(
			final String serviceName, final Request request,
			final Class<Response> responseClass) throws TException {
		TTransport transport = null;
		try {
			transport = this.transportManager.getTransport();
			TBinaryProtocol protocol = new TBinaryProtocol(transport);
			BaseService.Client client = new BaseService.Client(protocol) {
				/**
				 * 替换BaseService中的methodName（硬编码为execute)为对应的服务名。
				 */
				protected void sendBase(String methodName, TBase<?, ?> args)
						throws TException {
					super.sendBase(serviceName, args);
				}

				/**
				 * 替换BaseService中的methodName（硬编码为execute)为对应的服务名。
				 */
				protected void sendBaseOneway(String methodName,
						TBase<?, ?> args) throws TException {
					super.sendBaseOneway(serviceName, args);
				}

				/**
				 * 替换BaseService中的methodName（硬编码为execute)为对应的服务名。
				 */
				protected void receiveBase(TBase<?, ?> result, String methodName)
						throws TException {
					super.receiveBase(result, serviceName);
				}
			};
			ByteBuffer responseBuffer = client.execute(ByteBuffer.wrap(request
					.toByteArray()));
			return this.parseFrom(responseClass, responseBuffer.array());
		} finally {
			if (transport != null) {
				transport.close();
			}
		}
	}

	/**
	 * 将protobuf字节流转换为对应的message对象。
	 *
	 * @param messageClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T extends Message> T parseFrom(Class<T> messageClass,
			byte[] bytes) throws SystemException {
		T message = null;
		try {
			message = (T) MethodUtils.invokeStaticMethod(messageClass,
					"parseFrom", bytes);
		} catch (NoSuchMethodException | IllegalAccessException
				| InvocationTargetException e) {
			SystemException ex = new SystemException();
			ex.setErrorCode(500);
			ex.setMessage(e.getMessage());
			throw ex;
		}
		return message;
	}
}
