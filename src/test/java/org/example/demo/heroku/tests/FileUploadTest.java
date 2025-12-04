package org.example.demo.heroku.tests;

import org.example.demo.heroku.pages.FileUploadPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileUploadTest {

    private WebDriver driver;
    private FileUploadPage page;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/upload");

        page = new FileUploadPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void uploadFileSuccessfully() {
        // ficheiro de teste dentro do projeto
        File file = new File("src/test/resources/test-file.txt");

        // garantir que o ficheiro existe
        assertTrue(file.exists(), "O ficheiro de teste não existe!");

        page.uploadFile(file.getAbsolutePath());

        assertEquals("File Uploaded!", page.getResultTitleText());
        assertEquals("test-file.txt", page.getUploadedFileName());
    }
}
