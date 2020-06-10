package page_loaderF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

import java.util.concurrent.Semaphore;

import interfaces.PageLoaderInterface;
import utils.Page;

public class PageLoader extends Thread {

	private URL url;
	private Page page;
	private PageLoaderInterface loaderRemote;
	public static int countLoader = 0;
	private static Semaphore sem = new Semaphore(1);
	
	public PageLoader(String name, PageLoaderInterface loader,URL url) {
		loaderRemote = loader;
		this.url = url;
		this.setName(name);
		start();
		try {
			join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		countLoader+=1;
	}

	public Page getPage() {
		return page;
	}

	public URL getUrl() {
		return url;
	}

	@Override
	public void run() {
		System.out.println(" Il thread " +currentThread().getName() + " ha iniziato il download della pagina "); 
		download();
		System.out.println(" Il thread " +currentThread().getName() + " ha concluso il download della pagina ");
		try {
			Thread.sleep(1000);
			System.out.println("Il thread " +currentThread().getName() + " Ha iniziato a caricare la pagina sul server ");
			loadPageOnServer();
			System.out.println("Il thread " +currentThread().getName() + " Ha concluso il caricamento della pagina sul server ");
			sem.acquire();
			countLoader--;
			if(countLoader == 0) {
				System.out.println("Ultimo thread eseguito");
				MainPageLoader.closeProgram();
			}
				
			sem.release();	
		} catch (Exception e) {
			// TODO: handle exception
		}


	}

	private void download() {
		try {

			page = new Page(new ArrayList<String>());
			BufferedReader readr = 	new BufferedReader(new InputStreamReader(url.openStream()));  
			String line; 
			int max = 0;
			System.out.println("Download Iniziato");
			while ((line = readr.readLine()) != null && max < 200) { 
				page.addInfo(line);
				//System.out.println(line); //debug
				max++;
			} 

			readr.close(); 
		}
		catch(IOException io) {
			io.printStackTrace();
		}
	}

	private void loadPageOnServer() {
		try {
			loaderRemote.loadPageOnServer(getPage(),getUrl(),currentThread().getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
