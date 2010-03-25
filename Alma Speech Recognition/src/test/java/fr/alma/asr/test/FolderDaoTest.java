package fr.alma.asr.test;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.alma.asr.dao.FolderDao;
import fr.alma.asr.dao.impl.AbstractDaoImpl;
import fr.alma.asr.dao.impl.FolderDaoImpl;
import fr.alma.asr.entities.Folder;

/**
 * @author Cédric Krommenhoek
 */
public class FolderDaoTest extends TestCase {

	private FolderDao dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		AbstractDaoImpl.addSpecificProperty("hibernate.connection.url", "jdbc:h2:./test/db");
		this.dao = new FolderDaoImpl();
		this.dao.deleteAll();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		this.dao.deleteAll();
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.FolderDaoImpl#findDossierRacine()}.
	 */
	@Test
	public final void testFindDossierRacine() {
		Folder folder1 = new Folder("Dossier 1");
		Folder folder2 = new Folder("Dossier 2");
		folder2.addElement(folder1);
		Folder folder3 = new Folder("Dossier 3");
		Folder folder4 = new Folder("Dossier 4");
		folder4.addElement(folder2);
		folder4.addElement(folder3);
		
		long id = this.dao.create(folder4);
		
		assertEquals(id, this.dao.findDossierRacine().getId());
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#create(fr.alma.asr.entities.AbstractEntity)}.
	 */
	@Test
	public final void testCreate() {
		Folder folder = new Folder("Dossier");
		int nb = this.dao.findAll().size();
		long id = this.dao.create(folder);
		assertNotNull(id);
		assertEquals(nb + 1, this.dao.findAll().size());
		assertEquals(folder.getNom(), this.dao.find(id).getNom());
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#deleteFolder(java.lang.Long)}.
	 */
	@Test
	public final void testDelete() {
		Folder folder1 = new Folder("Dossier 1");
		Folder folder2 = new Folder("Dossier 2");
		folder2.addElement(folder1);
		Folder folder3 = new Folder("Dossier 3");
		Folder folder4 = new Folder("Dossier 4");
		folder4.addElement(folder2);
		folder4.addElement(folder3);
		this.dao.create(folder4);
		
		int nb = this.dao.findAll().size();
		this.dao.delete(folder3.getId());
		assertNull(this.dao.find(folder3.getId()));
		assertEquals(nb - 1, this.dao.findAll().size());
		
		this.dao.delete(folder2.getId());
		assertNull(this.dao.find(folder1.getId()));
		assertNull(this.dao.find(folder2.getId()));
		assertEquals(nb - 3, this.dao.findAll().size());
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#deleteAll()}.
	 */
	@Test
	public final void testDeleteAll() {
		Folder folder1 = new Folder("Dossier 1");
		Folder folder2 = new Folder("Dossier 2");
		folder2.addElement(folder1);
		Folder folder3 = new Folder("Dossier 3");
		Folder folder4 = new Folder("Dossier 4");
		folder4.addElement(folder2);
		folder4.addElement(folder3);
		this.dao.create(folder4);
		
		assertEquals(4, this.dao.findAll().size());
		this.dao.deleteAll();
		assertEquals(0, this.dao.findAll().size());
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#find(java.lang.Long)}.
	 */
	@Test
	public final void testFind() {
		Folder folder = new Folder("Dossier");
		long id = this.dao.create(folder);
		Folder newFolder = this.dao.find(id);
		
		assertNotNull(newFolder);
		assertEquals(folder, newFolder);	
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#findAll()}.
	 */
	@Test
	public final void testFindAll() {
		Folder folder1 = new Folder("Dossier 1");
		Folder folder2 = new Folder("Dossier 2");
		folder2.addElement(folder1);
		Folder folder3 = new Folder("Dossier 3");
		Folder folder4 = new Folder("Dossier 4");
		folder4.addElement(folder2);
		this.dao.create(folder4);
		
		List<Folder> folders = this.dao.findAll();
		assertNotNull(folders);
		assertTrue(folders.contains(folder1));
		assertTrue(folders.contains(folder2));
		assertFalse(folders.contains(folder3));
		assertTrue(folders.contains(folder4));
		assertEquals(3, folders.size());
	}
	
	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#findModules()}.
	 */
	@Test
	public final void testFindModules() {
		Folder folder1 = new Folder("Dossier 1");
		Folder folder2 = new Folder("Dossier 2");
		folder2.setModule();
		folder2.addElement(folder1);
		Folder folder3 = new Folder("Dossier 3");
		folder3.setModule();
		Folder folder4 = new Folder("Dossier 4");
		folder4.addElement(folder2);
		folder4.addElement(folder3);
		this.dao.create(folder4);
		
		List<Folder> modules = this.dao.findModules();
		assertNotNull(modules);
		assertFalse(modules.contains(folder1));
		assertTrue(modules.contains(folder2));
		assertTrue(modules.contains(folder3));
		assertFalse(modules.contains(folder4));
		assertEquals(2, modules.size());
	}

	/**
	 * Test method for {@link fr.alma.asr.dao.impl.AbstractDaoImpl#update(fr.alma.asr.entities.AbstractEntity)}.
	 */
	@Test
	public final void testUpdate() {
		Folder folder = new Folder("Dossier");
		long id = this.dao.create(folder);		
		Folder newFolder = this.dao.find(id);
		
		folder.setNom("Nouveau nom de dossier");
		this.dao.update(folder);
		assertNotSame(newFolder, this.dao.find(id));
		assertEquals(folder, this.dao.find(id));
	}

}
