package com.googlepay.app;

import com.googlepay.converter.AccountInfoJsonReader;
import com.googlepay.converter.AccountInfoJsonWriter;
import com.googlepay.model.AccountInfo;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;

public class GooglePayApp {
    public static void main(String[] args) {
        GooglePayApp googlepayApp=new GooglePayApp();
        googlepayApp.fetchBalance("4674674","+914874585858","hdfc");
        //googlepayApp.createAccountInPartner(new AccountInfo("Kishore Ojha","SAVINGS","CREATED","HDFC JUBILEE HILLS","+91744544","EPHFBB8763",780000.0f));
        //googlepayApp.updateAccountInPartner(new AccountInfo("Kishore Ojha","SAVINGS","CREATED","HDFC JUBILEE HILLS","++917457657","EPHFBB8763",780000.0f));
    }

    public void fetchBalance(String accountNo, String mobileNo, String bankName) {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:9090/upi/bank")
                .path("/{bank-name}")
                .path("/{account-no}")
                .resolveTemplate("bank-name", bankName)
                .resolveTemplate("account-no", accountNo)
                .matrixParam("mobile-no",mobileNo)
                .request()
                .header("Cookie","account-holder-name=mahesh")
                .get();
        String data= response.readEntity(String.class);
        System.out.println("Google pay successfully Fetched Amount from your bank :: "+data);
    }

    public void createAccountInPartner(AccountInfo accountInfo){
        Client client=ClientBuilder.newClient().register(AccountInfoJsonWriter.class);
        Response response=client.target("http://localhost:9090/HDFCBank/api/hdfc/accounts")
                .request()
                .header("Content-Type", "application/json")
                .post(Entity.json(accountInfo));
        String data=response.readEntity(String.class);
        System.out.println("Google pay successfully Created Account in Partner :: "+data);
    }

    public void updateAccountInPartner(AccountInfo accountInfo){
        Client client=ClientBuilder.newClient().register(AccountInfoJsonWriter.class).register(AccountInfoJsonReader.class);
        Response response=client.target("http://localhost:9090/HDFCBank/api/hdfc/accounts")
                .request()
                .header("Content-type","application/json")
                .header("Accept","application/json")
                .put(Entity.json(accountInfo));
        AccountInfo data=response.readEntity(AccountInfo.class);
        System.out.println(data.toString());
    }
}