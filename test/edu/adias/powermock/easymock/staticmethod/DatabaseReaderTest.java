package edu.adias.powermock.easymock.staticmethod;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DatabaseReader.class)
public class DatabaseReaderTest {
	
	@Test
	public void testGetById() {
		int inputId = 1;
		String returnValue = "JavaCodeGeeks";

		mockStatic(DatabaseReader.class);

		try {
			expect(DatabaseReader.getById(inputId))
				.andReturn(returnValue);
			
			replayAll();
			
			String actual = DatabaseReader.getById(inputId);
			
			verifyAll();
			assertEquals(returnValue, actual);
		} catch (SQLException e) {
			fail("No exception should be thrown.");
		}
	}
	
	@Test
	public void testGetByIdMockDatabase() {
		String query = "SELECT * FROM Foo WHERE Id = ?";
		int inputId = 1;
		String returnValue = "JavaCodeGeeks";
		
		Connection connectionMock = createMock(Connection.class);
		PreparedStatement preparedStatementMock = createMock(PreparedStatement.class);
		ResultSet resultSetMock = createMock(ResultSet.class);
		
		mockStatic(DriverManager.class);
		try {
			expect(DriverManager.getConnection(DatabaseReader.CONNECTION))
			    .andReturn(connectionMock);

			expect(connectionMock.prepareStatement(query))
				.andReturn(preparedStatementMock);
			
			expect(preparedStatementMock.executeQuery())
				.andReturn(resultSetMock);

			expect(resultSetMock.next())
				.andReturn(true);
			
			expect(resultSetMock.getString(0))
				.andReturn(returnValue);
			replayAll();
			
			String actual = DatabaseReader.getById(inputId);
		
			verifyAll();

			assertEquals(returnValue, actual);
		} catch (SQLException e) {
			fail("No exception should be thrown.");
		}
	}
}
