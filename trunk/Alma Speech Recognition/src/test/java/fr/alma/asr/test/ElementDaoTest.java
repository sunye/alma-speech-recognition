package fr.alma.asr.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.alma.asr.dao.ElementDao;
import fr.alma.asr.dao.impl.AbstractDaoImpl;
import fr.alma.asr.dao.impl.ElementDaoImpl;
import fr.alma.asr.entities.Element;
import fr.alma.asr.entities.Folder;
import fr.alma.asr.entities.Lesson;

/**
 * @author Cédric Krommenhoek
 */
public class ElementDaoTest extends TestCase {

	private ElementDao dao;
	private long idRacine;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		AbstractDaoImpl.addSpecificProperty("hibernate.connection.url", "jdbc:h2:./test/db");
		this.dao = new ElementDaoImpl();
		this.dao.deleteAll();
		
		Folder dossier1 = new Folder("Dossier 1 ");
		Folder dossier2 = new Folder("Dossier 2 ");
		dossier1.addElement(dossier2);
		Folder dossier3 = new Folder("Dossier 3 ");
		dossier1.addElement(dossier3);
		Lesson cours1 = new Lesson("Cours 1");
		dossier2.addElement(cours1);
		Lesson cours2 = new Lesson("Cours 2");
		dossier2.addElement(cours2);
		
		this.idRacine = this.dao.create(dossier1);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		this.dao.deleteAll();
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.ElementDaoImpl#delete(java.lang.Long)}.
	 */
	@Test
	public final void testDelete() {	
		Folder dossierRacine = (Folder) this.dao.find(this.idRacine);		
		int nb = this.dao.findAll().size();
		
		Lesson cours = new Lesson("Cours");
		dossierRacine.addElement(cours);
		this.dao.create(cours);
		this.dao.update(dossierRacine);
		assertNotNull(this.dao.find(cours.getId()));
		assertEquals(nb + 1, this.dao.findAll().size());
		this.dao.delete(cours.getId());
		assertNull(this.dao.find(cours.getId()));
		assertEquals(nb, this.dao.findAll().size());
		
		dossierRacine = (Folder) this.dao.find(this.idRacine);
		
		Folder dossier = new Folder("Dossier");
		dossierRacine.addElement(dossier);
		this.dao.create(dossier);
		this.dao.update(dossierRacine);
		assertNotNull(this.dao.find(dossier.getId()));
		assertEquals(nb + 1, this.dao.findAll().size());
		this.dao.delete(dossier.getId());
		assertNull(this.dao.find(dossier.getId()));
		assertEquals(nb, this.dao.findAll().size());
		
		this.dao.delete(dossierRacine.getId());
		assertEquals(0, this.dao.findAll().size());
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.ElementDaoImpl#findAllOfDossier(fr.alma.asr.entities.Folder)}.
	 */
	@Test
	public final void testFindAllOfDossier() {
		Folder dossierRacine = (Folder) this.dao.find(this.idRacine);
		assertEquals(2, this.dao.findAllOfDossier(dossierRacine).size());
		
		for (Element e : this.dao.findAllOfDossier(dossierRacine)) {
			if (e.getNom().endsWith("Dossier 2")) {
				assertEquals(2, this.dao.findAllOfDossier((Folder) e));
			} else if (e.getNom().endsWith("Dossier 3")) {
				assertEquals(0, this.dao.findAllOfDossier((Folder) e));
			}
		}
	}

}
