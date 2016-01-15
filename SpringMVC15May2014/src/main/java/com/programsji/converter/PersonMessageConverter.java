package com.programsji.converter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import liquibase.util.csv.CSVReader;
import liquibase.util.csv.CSVWriter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.programsji.bo.Person;

public class PersonMessageConverter extends
		AbstractHttpMessageConverter<Person> {

	public PersonMessageConverter() {
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	public PersonMessageConverter(MediaType supportedMediaType) {
		super(supportedMediaType);
	}

	public PersonMessageConverter(MediaType... supportedMediaTypes) {
		super(supportedMediaTypes);
	}

	@Override
	protected Person readInternal(Class<? extends Person> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		CSVReader reader = new CSVReader(new InputStreamReader(
				inputMessage.getBody()));
		List<String[]> rows = reader.readAll();
		Person person = null;
		for (String[] row : rows) {
			// person = (new Person(row[0], row[1]));
		}
		return person;
	}

	@Override
	protected void writeInternal(Person t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		CSVWriter writer = new CSVWriter(new OutputStreamWriter(
				outputMessage.getBody()));
		writer.writeNext(new String[] { "Rohit", "Malhotra" });
		writer.close();
	}

}
