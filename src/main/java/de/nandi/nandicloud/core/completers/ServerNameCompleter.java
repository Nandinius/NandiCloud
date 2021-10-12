package de.nandi.nandicloud.core.completers;

import de.nandi.nandicloud.api.api.NandiCloudAPI;
import de.nandi.nandicloud.api.objects.ServerObject;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

import java.util.List;
import java.util.stream.Collectors;

public class ServerNameCompleter implements Completer {
	@Override
	public void complete(LineReader lineReader, ParsedLine parsedLine, List<Candidate> list) {
		list.addAll(NandiCloudAPI.getUniversalAPI().getServers().stream().map(ServerObject::getName)
				.map(Candidate::new).collect(Collectors.toList()));
	}
}
