package com.betuiasi.server.service;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: identity.proto")
public final class AuthenticationServiceGrpc {

  private AuthenticationServiceGrpc() {}

  public static final String SERVICE_NAME = "AuthenticationService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.betuiasi.server.dto.Identity.AuthenticationRequest,
      com.betuiasi.server.dto.Identity.AuthenticationResponse> METHOD_AUTHENTICATE =
      io.grpc.MethodDescriptor.<com.betuiasi.server.dto.Identity.AuthenticationRequest, com.betuiasi.server.dto.Identity.AuthenticationResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "AuthenticationService", "authenticate"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.betuiasi.server.dto.Identity.AuthenticationRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.betuiasi.server.dto.Identity.AuthenticationResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.betuiasi.server.dto.Identity.ValidateRequest,
      com.betuiasi.server.dto.Identity.ValidateResponse> METHOD_VALIDATE =
      io.grpc.MethodDescriptor.<com.betuiasi.server.dto.Identity.ValidateRequest, com.betuiasi.server.dto.Identity.ValidateResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "AuthenticationService", "validate"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.betuiasi.server.dto.Identity.ValidateRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.betuiasi.server.dto.Identity.ValidateResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthenticationServiceStub newStub(io.grpc.Channel channel) {
    return new AuthenticationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthenticationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AuthenticationServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthenticationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AuthenticationServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class AuthenticationServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void authenticate(com.betuiasi.server.dto.Identity.AuthenticationRequest request,
        io.grpc.stub.StreamObserver<com.betuiasi.server.dto.Identity.AuthenticationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_AUTHENTICATE, responseObserver);
    }

    /**
     */
    public void validate(com.betuiasi.server.dto.Identity.ValidateRequest request,
        io.grpc.stub.StreamObserver<com.betuiasi.server.dto.Identity.ValidateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_VALIDATE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_AUTHENTICATE,
            asyncUnaryCall(
              new MethodHandlers<
                com.betuiasi.server.dto.Identity.AuthenticationRequest,
                com.betuiasi.server.dto.Identity.AuthenticationResponse>(
                  this, METHODID_AUTHENTICATE)))
          .addMethod(
            METHOD_VALIDATE,
            asyncUnaryCall(
              new MethodHandlers<
                com.betuiasi.server.dto.Identity.ValidateRequest,
                com.betuiasi.server.dto.Identity.ValidateResponse>(
                  this, METHODID_VALIDATE)))
          .build();
    }
  }

  /**
   */
  public static final class AuthenticationServiceStub extends io.grpc.stub.AbstractStub<AuthenticationServiceStub> {
    private AuthenticationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceStub(channel, callOptions);
    }

    /**
     */
    public void authenticate(com.betuiasi.server.dto.Identity.AuthenticationRequest request,
        io.grpc.stub.StreamObserver<com.betuiasi.server.dto.Identity.AuthenticationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_AUTHENTICATE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void validate(com.betuiasi.server.dto.Identity.ValidateRequest request,
        io.grpc.stub.StreamObserver<com.betuiasi.server.dto.Identity.ValidateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_VALIDATE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AuthenticationServiceBlockingStub extends io.grpc.stub.AbstractStub<AuthenticationServiceBlockingStub> {
    private AuthenticationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.betuiasi.server.dto.Identity.AuthenticationResponse authenticate(com.betuiasi.server.dto.Identity.AuthenticationRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_AUTHENTICATE, getCallOptions(), request);
    }

    /**
     */
    public com.betuiasi.server.dto.Identity.ValidateResponse validate(com.betuiasi.server.dto.Identity.ValidateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_VALIDATE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AuthenticationServiceFutureStub extends io.grpc.stub.AbstractStub<AuthenticationServiceFutureStub> {
    private AuthenticationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.betuiasi.server.dto.Identity.AuthenticationResponse> authenticate(
        com.betuiasi.server.dto.Identity.AuthenticationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_AUTHENTICATE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.betuiasi.server.dto.Identity.ValidateResponse> validate(
        com.betuiasi.server.dto.Identity.ValidateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_VALIDATE, getCallOptions()), request);
    }
  }

  private static final int METHODID_AUTHENTICATE = 0;
  private static final int METHODID_VALIDATE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AuthenticationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AuthenticationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTHENTICATE:
          serviceImpl.authenticate((com.betuiasi.server.dto.Identity.AuthenticationRequest) request,
              (io.grpc.stub.StreamObserver<com.betuiasi.server.dto.Identity.AuthenticationResponse>) responseObserver);
          break;
        case METHODID_VALIDATE:
          serviceImpl.validate((com.betuiasi.server.dto.Identity.ValidateRequest) request,
              (io.grpc.stub.StreamObserver<com.betuiasi.server.dto.Identity.ValidateResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class AuthenticationServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.betuiasi.server.dto.Identity.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AuthenticationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthenticationServiceDescriptorSupplier())
              .addMethod(METHOD_AUTHENTICATE)
              .addMethod(METHOD_VALIDATE)
              .build();
        }
      }
    }
    return result;
  }
}
