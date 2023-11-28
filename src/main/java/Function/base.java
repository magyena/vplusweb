package Function;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.*;


import io.github.bonigarcia.wdm.WebDriverManager;
import net.sf.expectit.Expect;
import net.sf.expectit.ExpectBuilder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static net.sf.expectit.filter.Filters.removeColors;
import static net.sf.expectit.filter.Filters.removeNonPrintable;
import static net.sf.expectit.matcher.Matchers.anyOf;
import static net.sf.expectit.matcher.Matchers.contains;

public class base {

	protected ChromeDriver driver;

	@BeforeTest
	public void setup() throws MalformedURLException, InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException { // FUNGSI GETDATA JSON
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String number() throws JSchException, IOException, InterruptedException {
		Random rand = new Random();

		// Generate a random 6-digit suffix for the phone number
		StringBuilder suffix = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			suffix.append(rand.nextInt(10));
		}

		// Append the prefix "0800" to the suffix
		return "+62800" + suffix.toString();
	}

	public String getOtp(String otpget) throws JSchException, IOException, InterruptedException {
		base generator = new base();
		String sshuser = "mncnow";
		String sshhost = "10.10.20.20";
		int sshport = 22;
		String sshpassword = "Welcome.21!--";
		String dbUser = "qa_vplus";
		String dbName = "sms_gateway";
		String dbHost = "10.10.128.146";
		String dbPort = "5432";
		String dbPassword = "qacredential";
		String Hasilnya = generator.number();
		// String SQLQuery = "SELECT otp FROM smsotp WHERE msisdn = '" + Hasilnya + "'ORDER BY created_at DESC LIMIT 1;";
		String SQLQuery = "SELECT otp FROM smsotp ORDER BY created_at DESC LIMIT 1;";

		JSch jsch = new JSch();

		Session session = jsch.getSession(sshuser, sshhost, sshport);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.setPassword(sshpassword);
		session.connect();

		System.out.println("Connected");

		Channel channel = session.openChannel("shell");
		channel.connect();

		Expect expect = new ExpectBuilder().withOutput(channel.getOutputStream())
				.withInputs(channel.getInputStream(), channel.getExtInputStream()).withEchoInput(System.out)
				.withEchoOutput(System.err).withInputFilters(removeColors(), removeNonPrintable()).build();
		System.out.println("Channel Connected to machine ");

		expect.sendLine("sudo ssh ubuntu@10.10.128.146 -i keypem/p-sms-otp-db.pem");
		expect.expect(contains("password"));
		expect.sendLine("Welcome.21!--");
		expect.expect(contains("ubuntu"));
		expect.sendLine("psql -U qa_vplus -d sms_gateway -h 10.10.128.146 -p 5432 -t -c \"" + SQLQuery + "\"");
		expect.expect(contains("Password"));
		expect.sendLine("qacredential");
		String sqlQueryResult = expect.expect(contains("ubuntu")).getBefore();
		String sqlQueryResult1 = "for user qa_vplus: \n" + sqlQueryResult;

		// Use regular expression to extract numeric value
		String otpRegex = "\\b\\d+\\b";
		Pattern pattern = Pattern.compile(otpRegex);
		java.util.regex.Matcher matcher = pattern.matcher(sqlQueryResult1);

		// Find the first match (assuming there is only one OTP)
		if (matcher.find()) {
			String otp = matcher.group();
			System.out.println(otp);
			Thread.sleep(889); // This sleep might not be necessary, depending on your use case.
			return otp; // Return the extracted OTP value
		} else {
			System.out.println("No OTP found in the SQL query result.");
			return null; // or throw an exception or handle it accordingly
		}
	}

	@AfterTest
	public void tearDown() {
		try {

			// Your existing teardown code
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
