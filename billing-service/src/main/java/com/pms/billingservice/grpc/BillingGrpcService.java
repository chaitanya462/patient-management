package com.pms.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, io.grpc.stub.StreamObserver<billing.BillingResponse> responseObserver) {

        log.info("createBillingAccount received request: {}", billingRequest.toString());
        //Business Logic - we can use normal springboot services and perform usual things what we did with normal springboot app without grpc.
        BillingResponse billingResponse = BillingResponse.newBuilder().setAccountId("12345").setStatus("Active").build();
        responseObserver.onNext(billingResponse);
        responseObserver.onCompleted();
    }
}
