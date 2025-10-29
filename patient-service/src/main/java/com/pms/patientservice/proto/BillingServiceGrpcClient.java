package com.pms.patientservice.proto;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private final BillingServiceGrpc.BillingServiceBlockingStub stub;

    public BillingServiceGrpcClient(@Value("${billing.service.address:localhost}") String serviceAddress,
                                    @Value("${billing.service.grpc.port:9091}") int port) {

        log.info("Connecting to Billing Service GRPC service at {} : {}", serviceAddress, port);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serviceAddress, port).usePlaintext().build();
        stub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(String patientId, String name, String email) {
        BillingRequest request = BillingRequest.newBuilder().setPatientId(patientId).setName(name).setEmail(email).build();
        log.info("Received response from billing service via GRPC: {}", request);
        return stub.createBillingAccount(request);
    }
}
