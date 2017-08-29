package com.cclip.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.csvreader.CsvWriter;

public class UtilCsv {

	public static void write(String path, String fileName, String[] columnNames, Object[][] data) throws IOException {

		SO.createFile(path, fileName, "");

		String outputFile = path + File.separatorChar + fileName + ".csv";

		CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), '|');

		for (int i = 0; i < data.length; i++) {
			csvOutput.write(columnNames[i]);
		}

		csvOutput.endRecord();

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {

				if (data[i][j] != null) {
					csvOutput.write(data[i][j].toString());
				} else {
					csvOutput.write(null);
				}

			}
			csvOutput.endRecord();
		}

		csvOutput.close();

	}

}
