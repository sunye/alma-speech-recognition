package fr.alma.asr.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.alma.asr.dao.ElementDao;
import fr.alma.asr.dao.impl.AbstractDaoImpl;
import fr.alma.asr.dao.impl.ElementDaoImpl;
import fr.alma.asr.entities.Folder;

/**
 * @author Cédric Krommenhoek
 */
public class AbstractDaoTest extends TestCase {
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#addSpecificProperty(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testAbstractDao() {
		AbstractDaoImpl.addSpecificProperty("hibernate.connection.url", "jdbc:h2:./test/db");
		ElementDao dao = new ElementDaoImpl();
		Folder dossier = new Folder("Dossier");
		long id = dao.create(dossier);
		AbstractDaoImpl.deconnexion();
		AbstractDaoImpl.deconnexion();
		assertNotNull(dao.find(id));
		assertEquals(dossier, dao.find(id));
	}

}
