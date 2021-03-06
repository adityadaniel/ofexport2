/*
 * Copyright 2015 Paul Sidnell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.psidnell.omnifocus.integrationtest;

import static org.junit.Assert.assertFalse;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.psidnell.omnifocus.ApplicationContextFactory;
import org.psidnell.omnifocus.model.Context;
import org.psidnell.omnifocus.model.Folder;
import org.psidnell.omnifocus.model.ProjectInfo;
import org.psidnell.omnifocus.model.Task;
import org.psidnell.omnifocus.sqlite.SQLiteDAO;
import org.springframework.context.ApplicationContext;

/**
 * @author psidnell
 *
 * Requires access to the OmniFocus database.
 */
public class SQLiteDAOTest {
    
    private static final ApplicationContext appContext = ApplicationContextFactory.getContext();
    
    private SQLiteDAO sqliteDAO;
    
    @Before
    public void setUp () {
        sqliteDAO = appContext.getBean("sqlitedao", SQLiteDAO.class);
    }
    
    @Test
    public void testDumpTables() throws SQLException, ClassNotFoundException {
        try (Connection c = sqliteDAO.getConnection()) {
            sqliteDAO.printTables(c);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    @Test
    public void testLoadTasks() throws ClassNotFoundException, SQLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        try (Connection c = sqliteDAO.getConnection()) {
            Collection<Task> tasks = sqliteDAO.load(c, SQLiteDAO.TASK_DAO);
            assertFalse (tasks.isEmpty());
        }
    }
    
    @Test
    public void testLoadFolders() throws ClassNotFoundException, SQLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        try (Connection c = sqliteDAO.getConnection()) {
            Collection<Folder> folders = sqliteDAO.load(c, SQLiteDAO.FOLDER_DAO);
            assertFalse (folders.isEmpty());
        }
    }
    
    @Test
    public void testLoadProjectsInfo() throws ClassNotFoundException, SQLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        try (Connection c = sqliteDAO.getConnection()) {
            Collection<ProjectInfo> projects = sqliteDAO.load(c, SQLiteDAO.PROJECT_INFO_DAO);
            assertFalse (projects.isEmpty());
        }
    }

    @Test
    public void testLoadContexts() throws ClassNotFoundException, SQLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        try (Connection c = sqliteDAO.getConnection()) {
            Collection<Context> contexts = sqliteDAO.load(c, SQLiteDAO.CONTEXT_DAO);
            assertFalse (contexts.isEmpty());
        }
    }
}
