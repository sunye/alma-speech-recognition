package fr.alma.asr.test;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.alma.asr.dao.FolderDao;
import fr.alma.asr.dao.LessonDao;
import fr.alma.asr.dao.impl.AbstractDaoImpl;
import fr.alma.asr.dao.impl.FolderDaoImpl;
import fr.alma.asr.dao.impl.LessonDaoImpl;
import fr.alma.asr.entities.Folder;
import fr.alma.asr.entities.Lesson;

/**
 * @author Cédric Krommenhoek
 */
public class LessonDaoTest extends TestCase {

	private LessonDao dao;
	private FolderDao folderDao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		AbstractDaoImpl.addSpecificProperty("hibernate.connection.url", "jdbc:h2:./test/db");
		this.dao = new LessonDaoImpl();
		this.dao.deleteAll();
		this.folderDao = new FolderDaoImpl();
		this.folderDao.deleteAll();
		
		Folder dossier = new Folder("Dossier");
		Lesson cours1 = new Lesson("Cours 1");
		dossier.addElements(cours1);
		Lesson cours2 = new Lesson("Cours 2");
		dossier.addElements(cours2);
		
		this.folderDao.create(dossier);		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		this.dao.deleteAll();
		this.folderDao.deleteAll();
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.LessonDaoImpl#delete(java.lang.Long)}.
	 */
	@Test
	public final void testDelete() {
		int nb = this.dao.findAll().size();
		Lesson cours = this.dao.findAll().get(0);
		this.dao.delete(cours.getId());
		assertNull(this.dao.find(cours.getId()));
		assertEquals(nb - 1, this.dao.findAll().size());
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.LessonDaoImpl#findAll(java.lang.Boolean)}.
	 */
	@Test
	public final void testFindAllBoolean() {
		int nb = this.dao.findAll().size();
		List<Lesson> cours = this.dao.findAll(true);
		assertNotNull(cours);
		assertEquals(nb, cours.size());		
		cours = this.dao.findAll(false);
		assertNotNull(cours);
		assertEquals(nb, cours.size());
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#create(fr.alma.asr.entities.AbstractEntity)}.
	 */
	@Test
	public final void testCreate() {
		int nb = this.dao.findAll().size();
		Lesson cours = new Lesson("Nouveau cours");
		long id = this.dao.create(cours);
		assertNotNull(id);
		assertEquals(cours, this.dao.find(id));
		assertEquals(nb + 1, this.dao.findAll().size());
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#deleteAll()}.
	 */
	@Test
	public final void testDeleteAll() {
		Lesson cours = this.dao.findAll().get(0);
		this.dao.deleteAll();
		assertNull(this.dao.find(cours.getId()));
		assertEquals(0, this.dao.findAll().size());
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#find(java.lang.Long)}.
	 */
	@Test
	public final void testFind() {
		Lesson cours = this.dao.findAll().get(0);
		assertNotNull(this.dao.find(cours.getId()));
		assertEquals(cours, this.dao.find(cours.getId()));
		this.dao.delete(cours.getId());
		assertNull(this.dao.find(cours.getId()));
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#findAll()}.
	 */
	@Test
	public final void testFindAll() {
		List<Lesson> cours = this.dao.findAll();
		for (Lesson l : cours) {
			assertNotNull(this.dao.find(l.getId()));
			this.dao.delete(l.getId());
		}
		assertEquals(0, this.dao.findAll().size());
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#update(fr.alma.asr.entities.AbstractEntity)}.
	 */
	@Test
	public final void testUpdate() {
		Lesson cours = new Lesson("Cours");
		long id = this.dao.create(cours);
		int nb = this.dao.findAll().size();
		Lesson newCours = this.dao.find(id);
		newCours.setDataEleve("Données de l'étudiant");
		this.dao.update(newCours);
		assertNotNull(this.dao.find(id));
		assertNotSame(cours, this.dao.find(id));
		assertEquals(newCours, this.dao.find(id));
		assertEquals(nb, this.dao.findAll().size());
	}

}
