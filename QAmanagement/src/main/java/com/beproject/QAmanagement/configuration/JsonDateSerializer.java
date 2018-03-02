package com.beproject.QAmanagement.configuration;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.springframework.stereotype.Component;


@Component

public class JsonDateSerializer extends com.fasterxml.jackson.databind.JsonSerializer<Date>{

private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:ss");


@Override
public void serialize(Date date, com.fasterxml.jackson.core.JsonGenerator gen,
		com.fasterxml.jackson.databind.SerializerProvider arg2)
		throws IOException, com.fasterxml.jackson.core.JsonProcessingException {
	String formattedDate = dateFormat.format(date);	

	gen.writeString(formattedDate);
	}

}