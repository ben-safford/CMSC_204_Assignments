
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Ben Safford
 *
 */
public class PasswordCheckerTest_STUDENT {
	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			PasswordCheckerUtility.isValidLength("short");
			fail("Didn't throw an exception for a short password");
		} catch (LengthException e) {
			
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("DogBone99").getAsBoolean());
			PasswordCheckerUtility.hasUpperAlpha("dogbone99");
			fail("Didn't throw an exception for an all-lowercase password");
		} catch (NoUpperAlphaException e) {
			
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha("DogBone99").getAsBoolean());
			PasswordCheckerUtility.hasLowerAlpha("DOGBONE99");
			fail("Didn't throw an exception for an all-uppercase password");
		} catch (NoLowerAlphaException e) {
			
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			PasswordCheckerUtility.isWeakPassword("DogBone99");
			fail("Didn't throw an exception for a weak password");
		} catch (WeakPasswordException e) {
			
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("DogBone99!"));
			PasswordCheckerUtility.isValidPassword("DogBooone99!");
		} catch (Exception e) {
			assertTrue(e instanceof InvalidSequenceException);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("DogBone99!"));
			PasswordCheckerUtility.isValidPassword("DogBoneNinetyNine!");
		} catch (Exception e) {
			assertTrue(e instanceof NoDigitException);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
		assertTrue(PasswordCheckerUtility.isValidPassword("DogBone99!"));
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> pws = new ArrayList<String>();
		pws.add("short");
		pws.add("DogBone99!");
		pws.add("DogBooone99!");
		pws.add("dogbone99!");
		pws.add("DOGBONE99!");
		ArrayList<String> outpws = PasswordCheckerUtility.getInvalidPasswords(pws);
		System.out.println(outpws.get(0));
		assertTrue(outpws.get(0).equals(pws.get(0) + " -> " + new LengthException().getMessage()));
		assertTrue(outpws.get(1).equals(pws.get(2) + " -> " + new InvalidSequenceException().getMessage()));
		assertTrue(outpws.get(2).equals(pws.get(3) + " -> " + new NoUpperAlphaException().getMessage()));
		assertTrue(outpws.get(3).equals(pws.get(4) + " -> " + new NoLowerAlphaException().getMessage()));
	}
	
}
