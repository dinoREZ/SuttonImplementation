package de.fhws.fiw.fds.sutton.client.converters;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;

public class DateTimeConverter implements Converter<LocalDateTime> {
	@Override
	public void serialize(final LocalDateTime convert, final ObjectWriter objectWriter,
			final Context context) throws Exception {
		final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		final ZonedDateTime zoned = ZonedDateTime.of(convert, ZoneId.systemDefault());
		final String output = zoned.format(formatter);
		objectWriter.writeString(output);
	}

	@Override
	public LocalDateTime deserialize(final ObjectReader objectReader, final Context context)
			throws Exception {
		final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		final String date = objectReader.valueAsString();
		return LocalDateTime.from(formatter.parse(date));
	}
}
