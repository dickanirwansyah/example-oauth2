package id.dicka.oauth2.productservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}

@Component
class testCurlOAuth2 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		String url = "http://localhost:8881/oauth/token?username=dickanirwansyah&password=password&grant_type=password";
		URL urlOauth2 = new URL(url);
		String encoding = Base64.getEncoder().encodeToString(("USER_CLIENT_APP:password").getBytes("UTF-8"));

		HttpURLConnection urlConnection = (HttpURLConnection) urlOauth2.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setDoOutput(true);
		urlConnection.setRequestProperty("Authorization", "Basic "+encoding);

		InputStream content = (InputStream) urlConnection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(content));
		String line;
		while ((line = in.readLine()) != null){
			System.out.println("TOKEN ACCESS => "+line);
		}
	}
}
