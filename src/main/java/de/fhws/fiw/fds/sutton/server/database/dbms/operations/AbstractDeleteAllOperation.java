// Copyright 2022 Peter Braun
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.fhws.fiw.fds.sutton.server.database.dbms.operations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import de.fhws.fiw.fds.sutton.server.database.dbms.IPersistency;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public abstract class AbstractDeleteAllOperation
        extends AbstractDatabaseOperation<Void, NoContentResult> {
    protected PreparedStatement databaseStatement;

    protected String databaseSQLStatement;

    public AbstractDeleteAllOperation(final IPersistency persistency) {
        super(persistency);
    }

    @Override
    protected NoContentResult executeDatabaseOperations() throws SQLException {
        createDatabaseSQLStatement();

        createDatabaseStatement();

        executeDatabaseStatement();

        closeDatabaseStatement();

        return new NoContentResult();
    }

    protected void createDatabaseSQLStatement() {
        this.databaseSQLStatement = "DELETE FROM " + getTableName() + " WHERE id > 0";
    }

    protected void createDatabaseStatement() throws SQLException {
        this.databaseStatement =
                this.databaseConnection.prepareStatement(this.databaseSQLStatement);
    }

    protected void executeDatabaseStatement() throws SQLException {
        this.databaseStatement.executeUpdate();
    }

    protected void closeDatabaseStatement() throws SQLException {
        if (this.databaseSQLStatement != null) {
            this.databaseStatement.close();
        }
    }

    protected abstract String getTableName();

    @Override
    protected NoContentResult createDatabaseError() {
        final NoContentResult returnValue = new NoContentResult();
        returnValue.setError();
        return returnValue;
    }

}
