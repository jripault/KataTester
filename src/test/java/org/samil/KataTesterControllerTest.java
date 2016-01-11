package org.samil;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.samil.internal.security.SandboxSecurityPolicy;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.security.Policy;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * User: Samil
 * Date: 11/01/2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class KataTesterControllerTest {
    @BeforeClass
    public static void init(){
        Policy.setPolicy(new SandboxSecurityPolicy());
        System.setSecurityManager(new SecurityManager());
    }


    RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void shouldStringCalculatorGetNoErrors() throws Exception {

        // Given
        String fileContent = getFileContent("stringCalculator/working.txt");

        // When
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/kata/test/STRINGCALCULATOR", fileContent, String.class);

        // Then
        assertThat(responseEntity.getBody()).isEqualTo("All tests pass!");
    }
    @Test
    public void shouldStringCalculatorGetSomeErrors() throws Exception {

        // Given
        String fileContent = getFileContent("stringCalculator/withErrors.txt");

        // When
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/kata/test/STRINGCALCULATOR", fileContent, String.class);

        // Then
        assertThat(responseEntity.getBody()).isEqualTo("There are some errors");
    }
    @Test
    public void shouldStringCalculatorNotBeHacked() throws Exception {

        // Given
        String fileContent = getFileContent("stringCalculator/accessDenied.txt");

        // When
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/kata/test/STRINGCALCULATOR", fileContent, String.class);

        // Then
        assertThat(responseEntity.getBody()).isEqualTo("Don't try to hack!");
    }

    private String getFileContent(String filename) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }
}
