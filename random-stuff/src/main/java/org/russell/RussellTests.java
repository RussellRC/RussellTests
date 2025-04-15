package org.russell;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class RussellTests {

	protected static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Map<Integer, String> columnCache = new HashMap<>();
	private static final SecureRandom sr = new SecureRandom();

	public static void main(String[] args) throws Exception {
		// 5 6 7 10 4 3 8 10
		// 3 3 3  3 3 3 8 10 = 36
		// 5 6 7 10 4 3 3 3 = 41

		//  5  6  7 10  4  3  8 10
		//  5 11 18 28 20 18 26 36
		// 41 36 30 23 13 9 16 10
	}

	private static String removeSlashPrefix(@Nonnull String path) {
		if (path.charAt(0) == '/') {
			return path.substring(1);
		}
		return path;
	}

	public static String nextString(final int length) {
		final StringBuilder sb = new StringBuilder();
		while (sb.length() < length) {
			sb.append(Character.forDigit(sr.nextInt(36), 36));
		}
		return sb.toString();
	}

	public static void upperDiagonal() {
		for (int i = 1; i < 10; i++) {
			for (int y = 0, x = i; x < 10; y++, x++) {
				System.out.print(y + "," + x + "  ");
			}
			System.out.println();
		}
	}

	public static void testToColumn() {
		String toCol = "";
		for (int i = 0; toCol.length() < 3; i++) {
			toCol = toColumn(i);
			System.out.println(i + " = " + toCol);
		}
	}

	public static String toColumn(int num) {
		String column = columnCache.get(num);
		if (column != null) {
			return column;
		}
		if (num >= 0 && num < 26) {
			column = Character.toString((char) ('A' + num));
			columnCache.put(num, column);
			return column;
		} else if (num > 25) {
			column = toColumn((num / 26) - 1) + toColumn(num % 26);
			columnCache.put(num, column);
			return column;
		} else
			throw new RuntimeException("Invalid Column #" + (num + 1));
	}

	public static void filter() {
		final List<String> l1 = Arrays.asList("1", "2", "3");
		final List<String> l2 = Collections.emptyList();

		final List<List<String>> lists = Arrays.asList(l1, l2);

		final List<List<String>> emptyLists = lists.stream().filter(l -> l.isEmpty()).collect(Collectors.toList());
		final List<List<String>> nonEmptyLists = lists.stream().filter(l -> !(l.isEmpty()))
			.collect(Collectors.toList());

		System.out.println("filter l.isEmpty():" + emptyLists);
		System.out.println("filter l.isNotEmpty():" + nonEmptyLists);

		System.out.println(emptyLists.size());
	}

	public static void jsonPath() throws Exception {
		final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("jsonPathTest.json");
		final String json = new Scanner(in, "UTF-8").useDelimiter("\\A").next();

		List<String> authors = JsonPath.read(json, "$.store.book[*].author");
		// System.out.println(authors);

		final JsonNode rootNode = MAPPER.readValue(json, JsonNode.class);
		printFieldKeys(rootNode, "$");
	}

	private static void printFieldKeys(JsonNode rootNode, String parent) {
		final Iterator<Entry<String, JsonNode>> fieldIt = rootNode.fields();
		while (fieldIt.hasNext()) {
			final Entry<String, JsonNode> next = fieldIt.next();
			final JsonNode value = next.getValue();
			final String path = parent + "." + next.getKey();

			if (value.isValueNode()) {
				System.out.println(path + " = " + value.asText());
			} else {
				System.out.println(path);
			}

			if (value.isArray()) {
				for (int i = 0; i < value.size(); i++) {
					printFieldKeys(value.get(i), path + "[" + i + "]");
				}
			} else {
				printFieldKeys(value, path);
			}
		}
	}

	public static void md5() throws Exception {
		final MessageDigest md = MessageDigest.getInstance("MD5");
		for (int i = 0; i < 10; i++) {
			System.out.println(DigestUtils.md5Hex("russell".getBytes(StandardCharsets.UTF_8)));
			Thread.sleep(RandomUtils.nextInt(1000, 5000));
		}
	}

	public static void zonedDateTime() {
		LocalDateTime dt = LocalDateTime.now();
		ZonedDateTime zdt = dt.atZone(ZoneId.of("UTC"));
		System.out.println(zdt.toString());

	}

	static void csv() throws Exception {
		String s, translate;

		// s =
		// "\"\"\"double quoted cell with trailing spaces\"\" \",\" \"\"double quoted
		// cell with leading space\"\"\"";

		s = "\"comma,\"\"separated\"\",cell1\"" + "," + "\"comma,separated,cell2\"";
		System.out.println(s);

		CSVReader csvr = new CSVReader(new StringReader(s));
		String[] arr = csvr.readNext();
		System.out.println(Arrays.toString(arr));

		CSVParser parser = new CSVParser();
		arr = parser.parseLine(s);
		System.out.println(Arrays.toString(arr));
	}

	public static void utcDate() {
		System.out.println("user.timezone:" + System.getProperty("user.timezone"));
		System.out.println("default timezone: " + TimeZone.getDefault().getID());
		// System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));

		final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		fmt.setTimeZone(TimeZone.getTimeZone("utc"));

		System.out.println(fmt.format(new Date(System.currentTimeMillis())));
	}

	public static void dateformat() throws Exception {
		// "2014-01-20T13:42:26Z"
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.out.println(fmt.parse("2014-01-20T13:42:26Z").getTime());
	}

	public static void spaces() {
		String regex = "\\s*(\\s|,|\\r|\\n)\\s*";

		String keysToSearch = ",some text, with,commas , and \r\n spaces\rand lines\nand \n\rthings,,\r\n\n\r  ,";
		System.out.println(keysToSearch);
		final String string = Arrays.stream(keysToSearch.split(regex))
			.filter(e -> !e.trim().isEmpty())
			.map(t -> {
				if (!t.endsWith("*")) {
					return t.concat("*");
				}
				return t;
			})
			.collect(Collectors.joining(","));

		System.out.println("+++");
		System.out.println(string);
	}

	private static class NodeInfo {
		final String id;
		final String label;
		final String href;
		final String tooltip;

		private NodeInfo(String id, String label, String href, String tooltip) {
			this.id = id;
			this.label = label;
			this.href = href;
			this.tooltip = tooltip;
		}

		@Override
		public String toString() {
			return "NodeInfo{" +
				"id='" + id + '\'' +
				", label='" + label + '\'' +
				", href='" + href + '\'' +
				", tooltip='" + tooltip + '\'' +
				'}';
		}
	}

}
