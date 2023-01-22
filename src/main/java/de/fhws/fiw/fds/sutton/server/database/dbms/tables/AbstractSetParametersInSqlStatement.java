package de.fhws.fiw.fds.sutton.server.database.dbms.tables;

import de.fhws.fiw.fds.sutton.server.database.dbms.operations.PreparedStatementHelper;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Function;

/**
 * The AbstractSetParametersInSqlStatement class helps by setting the SQL statement for the <strong>INSERT</strong>
 * and the <strong>UPDATE</strong> operations to be executed in the database. The class takes care of replacing the
 * placeholder in a {@link PreparedStatement} by the values of the attributes of an instance of {@link AbstractModel}
 *
 * @param <T> an instance of {@link AbstractModel}
 * */
public abstract class AbstractSetParametersInSqlStatement<T extends AbstractModel>
{
	/**
	 * Sets all parameters in the given {@link PreparedStatement} to the corresponding attributes of the instance of
	 * {@link AbstractModel}
	 *
	 * <br>
	 *
	 * assuming that <i>Person</i> is an implementation of {@link AbstractModel} and its attributes are the following:
	 * <pre>{@code
	 * public class Person extends AbstractModel {
	 *     String firstname;
	 *     String lastname;
	 * }
	 * }</pre>
	 *
	 * then using the method the following SQL statement:
	 * <br>
	 * "INSERT INTO {TABLE_NAME} ( firstname, lastname ) VALUES (?,?)"
	 * <br>
	 * will be set to this statement:
	 * <br>
	 * "INSERT INTO {TABLE_NAME} ( firstname, lastname ) VALUES (person.firstname,person.lastname)"
	 *
	 * @param model {@link T} the model to be used to set the SQL statement
	 * @param statement {@link PreparedStatement} the prepared statement to be set
	 * @throws   SQLException if parameterIndex does not correspond to a parameter marker in the SQL statement; if a
	 * database access error occurs or this method is called on a closed PreparedStatement
	 * */
	public void set( final T model, final PreparedStatement statement ) throws
		SQLException
	{
		for ( final Map.Entry<Integer, Function<T, Object>> e : getMapOfIndexToGetterMethod( ).entrySet( ) )
		{
			set( statement, e.getKey( ), e.getValue( ).apply( model ) );
		}
	}

	/**
	 * @return a map {@link Map}, whose keys define the order of the column name in the SQL statement, and
	 * its values are getter methods of an instance of {@link AbstractModel}. The resulting map will be used to
	 * replace the placeholders in the SQL statement with attributes of the instance of AbstractModel.
	 * <br>
	 * given is the following SQL statement to be prepared to execution:
	 * <br>
	 * "INSERT INTO {TABLE_NAME} (name, birthdate) VALUES (?,?)"
	 * <br>
	 * then the resulting map should look as the following:
	 * <pre> {@code
	 * Map<Integer, R extends AbstractModel> map = new Map<>();
	 * map.put(1, R::getName());
	 * map.put(2, R::getBirthdate());
	 * return map;
	 * }
	 * </pre>
	 *
	 * <br>
	 * where the first key of the map is 1 because name is the first column name to set its value in the previous SQL
	 * statement.
	 * */
	protected abstract Map<Integer, Function<T, Object>> getMapOfIndexToGetterMethod( );

	private void set( final PreparedStatement statement, final int index, final Object value ) throws SQLException
	{
		PreparedStatementHelper.set( statement, index, value );
	}

}
