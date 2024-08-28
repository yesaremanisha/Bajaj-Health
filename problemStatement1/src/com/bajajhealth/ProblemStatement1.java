package com.bajajhealth;

public class ProblemStatement1 {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java -jar test.jar <PRN Number> <path to JSON file>");
			return;
		}

		String prn = args[0].toLowerCase().replaceAll("\\s+", "");
		String filePath = args[1];
		String destination = JsonUtils.getDestinationValue(filePath);
		if (destination == null) {
			System.out.println("Destination key not found in the JSON file.");
			return;
		}

		String randomStr = HashUtils.generateRandomString(8);
		String input = prn + destination + randomStr;
		String hash = HashUtils.generateMD5Hash(input);

		System.out.println(hash + ";" + randomStr);
	}
}
