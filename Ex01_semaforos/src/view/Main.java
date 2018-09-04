package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Pessoa;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.concurrent.Semaphore;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon imgPorta = new ImageIcon("personagens\\porta_0.png");
		imgPorta.setImage(imgPorta.getImage().getScaledInstance(37, 39, 100));
		
		JLabel lblPessoa3 = new JLabel();
		lblPessoa3.setBounds(185, 239, 56, 31);
		contentPane.add(lblPessoa3);
		
		JLabel lblPessoa4 = new JLabel();
		lblPessoa4.setBounds(12, 132, 56, 31);
		contentPane.add(lblPessoa4);
		
		JLabel lblPessoa2 = new JLabel();
		lblPessoa2.setBounds(374, 132, 56, 31);
		contentPane.add(lblPessoa2);
		
		JLabel lblPorta = new JLabel(imgPorta);
		lblPorta.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPorta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorta.setBounds(185, 110, 37, 39);
		contentPane.add(lblPorta);
		
		JLabel lblPessoa1 = new JLabel();
		lblPessoa1.setBounds(185, 13, 56, 31);
		contentPane.add(lblPessoa1);
		
		Semaphore semaphore = new Semaphore(1);
		
		String[] framesPorta = new String[] {"personagens\\porta_0.png","personagens\\porta_1.png", "personagens\\porta_2.png", "personagens\\porta_3.png"};
		
		String[] frames = new String[] {"personagens\\personagem1_0.png", "personagens\\personagem1_1.png", "personagens\\personagem1_2.png"};
		Pessoa pessoa1 = new Pessoa(0, (-1), frames, lblPorta, framesPorta, lblPessoa1, semaphore);
		pessoa1.start();
		
		frames = new String[] {"personagens\\personagem2_0.png", "personagens\\personagem2_1.png", "personagens\\personagem2_2.png"};
		Pessoa pessoa2 = new Pessoa((-1), 0, frames, lblPorta, framesPorta, lblPessoa2, semaphore);
		pessoa2.start();
		
		frames = new String[] {"personagens\\personagem3_0.png", "personagens\\personagem3_1.png", "personagens\\personagem3_2.png"};
		Pessoa pessoa3 = new Pessoa(0, 1, frames, lblPorta, framesPorta, lblPessoa3, semaphore);
		pessoa3.start();
		
		frames = new String[] {"personagens\\personagem4_0.png", "personagens\\personagem4_1.png", "personagens\\personagem4_2.png"};
		Pessoa pessoa4 = new Pessoa(1, 0, frames, lblPorta, framesPorta, lblPessoa4, semaphore);
		pessoa4.start();
	}
}
