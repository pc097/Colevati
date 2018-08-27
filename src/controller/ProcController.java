package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class ProcController {
	public ProcController() {
		super();
	}

	public void Prop() {
		String so = System.getProperty("os.name");
		String arch = System.getProperty("os.arch");
		String version = System.getProperty("os.version");
		System.out.println(so);
		System.out.println(arch);
		System.out.println(version);
	}

	public void leProcesso(String path) {
		try {
			Process proc = Runtime.getRuntime().exec(path);
			InputStream fluxo = proc.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
		} catch (IOException e) {
			String erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro);
			e.printStackTrace();
		}
	}

	public void chamaProcesso(String path) {
		try {
			Runtime.getRuntime().exec(path);
		} catch (IOException e) {
			String erro = e.getMessage();
			if (erro.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(path);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					e.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		}

	}

	public void mataProcesso(String infoProcesso) {
		String cmdPid = "TASKKILL /PID ";
		String cmdNome = "TASKKILL /IM ";
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		try {
			pid = Integer.parseInt(infoProcesso);
			buffer.append(cmdPid);
			buffer.append(pid);
		} catch (NumberFormatException e) {
			buffer.append(cmdNome);
			buffer.append(infoProcesso);
		}
		chamaProcesso(buffer.toString());
	}
}
