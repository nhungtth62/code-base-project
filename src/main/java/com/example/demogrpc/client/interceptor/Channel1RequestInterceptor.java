package com.example.demogrpc.client.interceptor;

import com.example.demogrpc.server.Constants;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Channel1RequestInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> methodDescriptor,
            CallOptions callOptions,
            Channel channel) {

        return channel.newCall(methodDescriptor, callOptions);

//        return new ForwardingClientCall.SimpleForwardingClientCall<>(
//                channel.newCall(methodDescriptor, callOptions)) {
//            @Override
//            public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
//                var userToken = "Bearer eyJ4NXQiOiJaalJtWVRNd05USmpPV1U1TW1Jek1qZ3pOREkzWTJJeU1tSXlZMkV6TWpkaFpqVmlNamMwWmciLCJraWQiOiJaalJtWVRNd05USmpPV1U1TW1Jek1qZ3pOREkzWTJJeU1tSXlZMkV6TWpkaFpqVmlNamMwWmdfUlMyNTYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0aHV5bnQ1MyIsImF1ZCI6IkJyb0ZVbTFrZjZXdnRXSjdNaE1BSk9uZGxWY2EiLCJuYmYiOjE2Nzc2NjE3NDIsImF6cCI6IkJyb0ZVbTFrZjZXdnRXSjdNaE1BSk9uZGxWY2EiLCJzY29wZSI6Im9wZW5pZCIsImlzcyI6Imh0dHBzOlwvXC93c28yaXMtc2l0LnRjYnMuY29tLnZuXC9vYXV0aDJcL3Rva2VuIiwibmFtZSI6Ik5ndXnhu4VuIFRodSAgVGjDunkiLCJncm91cHMiOlsiVENCUy5DT00uVk5cL1Rlc3Qtbm90aWZpY2F0aW9uIiwiVENCUy5DT00uVk5cL0lCX0JDUCIsIkludGVybmFsXC9ldmVyeW9uZSIsIkFwcGxpY2F0aW9uXC9SVUJJS19NQUtFUiIsIlRDQlMuQ09NLlZOXC9JQiBTY3J1bXMiLCJBcHBsaWNhdGlvblwvUlVCSUtfUEVSTUlTU0lPTiIsIlRDQlMuQ09NLlZOXC9qaXJhLXVzZXJzIiwiVENCUy5DT00uVk5cL0lCX0lUIiwiVENCUy5DT00uVk5cL2NvbmZsdWVuY2UtdXNlcnMiLCJUQ0JTLkNPTS5WTlwvSUIgTmV3cyIsIlRDQlMuQ09NLlZOXC9DRF9OT1RJIiwiVENCUy5DT00uVk5cL0RpIGNodXnhu4NuIFRy4bqnbiBEdXkgSMawbmcgMDIuMjAyMiIsIlRDQlMuQ09NLlZOXC9JQl9RRSAoQThBQjk3RkIpIiwiQXBwbGljYXRpb25cL1JVQklLX1ZJRVdfUEVSTUlTU0lPTiIsIlRDQlMuQ09NLlZOXC9ydWJpay11c2VycyJdLCJleHAiOjE2Nzc2NjUzNDIsImlhdCI6MTY3NzY2MTc0MiwianRpIjoiYWI0NDI4NDMtMWYzOS00M2VkLWEzZTgtMTFhZGFiOGE5YmMzIiwiZW1haWwiOiJ0aHV5bnQ1M0B0Y2JzLmNvbS52biJ9.VOBKfo-d41HuoyDXnXRuJR2b8-fP_PEAKiuB5q80N0kPFCU9_YyrgfBAz8tTPxXILJ5cWDXmJAvnFmtPMikuPmEVK2MX0AbDQ9lAMK8aHVPssecafcGn6wEOKF8UZjKfVZ1GLvSJM4tD8IzybbH9tSYEKFwU1Vb3WoHd8s_QQeDnzY4G9tZpfvBoGMi03n3ZL4LIdtMBnAuUgUIeoV2f9sa5V6b-Gnm8_LBlSlEme13a9j7hrBTzCZnKMb3CwL2Q0PBwuoGTssPQQzO2a3mZR4EdYzUNT9ZCTtwZh4h0Gjo2Ea4juS5vf6rCJJVBlGtGdr0alsLCv7Ydb2nrmsO59A";
//                log.info("Setting userToken in header");
//                headers.put(Metadata.Key.of(Constants.Authorization, Metadata.ASCII_STRING_MARSHALLER), userToken);
//                super.start(responseListener, headers);
//            }
//        };
    }
}
