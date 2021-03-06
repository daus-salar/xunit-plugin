package org.jenkinsci.plugins.xunit.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;


/**
 * Unit tests for XUnitValidationService class.
 *
 * @author Maciek Siemczyk
 */
public class XUnitValidationServiceTest {
    /**
     * Helper class for creating temporary workspace.
     */
    @Rule
    public TempWorkspace tempWorkspace = new TempWorkspace();

    /**
     * System Under Test (SUT).
     */
    private XUnitValidationService xUnitValidationService = new XUnitValidationService();

    @Test
    public void CheckFileIsNotEmpty_GivenEmptyFile_ReturnsFalse() throws Exception {
        File testFile = new File(tempWorkspace.getDir(), "empty.txt");
        boolean created = testFile.createNewFile();
        Assert.assertTrue(created);

        Assert.assertFalse(
                "CheckFileIsNotEmpty returned true.",
                xUnitValidationService.checkFileIsNotEmpty(testFile));
    }

    @Test
    public void CheckFileIsNotEmpty_GivenNotEmptyFile_ReturnsTrue() throws Exception {
        File testFile = CreateNotEmtyFile();

        Assert.assertTrue(
                "CheckFileIsNotEmpty returned false.",
                xUnitValidationService.checkFileIsNotEmpty(testFile));
    }

    /**
     * Helper method that will create a file with some text in it.
     *
     * @return Created file.
     * @throws Exception when there is a problem with writing to the file.
     */
    private File CreateNotEmtyFile() throws Exception {
        File testFile = new File(tempWorkspace.getDir(), "notempty.txt");

        FileOutputStream stream = new FileOutputStream(testFile);
        stream.write("This is just not empty test file!".getBytes());
        stream.close();

        return testFile;
    }
}
