package com.googlepay.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class GooglePayApp {
    public static void main(String[] args) {
        GooglePayApp googlepayApp=new GooglePayApp();
        googlepayApp.fetchBalance("4674674","+914874585858");
    }

    public void fetchBalance(String accountNo, String mobileNo) {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:9090/HDFCBank/api/hdfc/accounts")
                .path("/{account-no}")
                .resolveTemplate("account-no", accountNo)
                .matrixParam("mobile-no",mobileNo)
                .request()
                .header("Cookie","account-holder-name=mahesh")
                .get();
        String data= response.readEntity(String.class);
        System.out.println("Google pay successfully Fetched Amount from your bank :: "+data);
    }
}