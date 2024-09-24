package com.googlepay.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlepay.model.AccountInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Consumes({MediaType.APPLICATION_JSON})
@Provider
public class AccountInfoJsonReader implements MessageBodyReader<AccountInfo> {

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public AccountInfo readFrom(Class<AccountInfo> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, AccountInfo.class);
    }
}
