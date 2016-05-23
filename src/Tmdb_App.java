import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Window.Type;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import java.awt.Button;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JDesktopPane;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
//import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

import info.movito.themoviedbapi.TmdbApi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JTable;

//User Defined
import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.Multi.MediaType;
import info.movito.themoviedbapi.TmdbSearch.*;
import info.movito.themoviedbapi.model.*;
import info.movito.themoviedbapi.model.changes.*;
import info.movito.themoviedbapi.model.config.*;
import info.movito.themoviedbapi.model.core.*;
import info.movito.themoviedbapi.model.keywords.*;
import info.movito.themoviedbapi.model.people.*;
import info.movito.themoviedbapi.model.tv.*;
import info.movito.themoviedbapi.tools.*;
import javax.swing.JSeparator;


public class Tmdb_App {

	//User Defined
	TmdbApi tmdbApi;
	MovieModel movieModel;
	PeopleModel peopleModel;
	TvModel tvModel;
	Search search;
	
	
	
	private JFrame frmL;
	private final Action action = new SwingAction();
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtSearchMoviesPeople;
	private JButton searchButton;
	private JTable tableMovie;
	private JTable tablePeople;
	private JTable tableTv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tmdb_App window = new Tmdb_App();
					window.frmL.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tmdb_App() {
		initialize();
		
		//User Defined
		
		tmdbApi = new TmdbApi("811ffa781385ed56e1ec64c193eb93f4");
		
		movieModel = new MovieModel();
		peopleModel = new PeopleModel();
		tvModel = new TvModel();
		
		search = new Search(tmdbApi, movieModel, peopleModel, tvModel);
		
		tableMovie.setModel(movieModel);
		tablePeople.setModel(peopleModel);
		tableTv.setModel(tvModel);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmL = new JFrame();
		frmL.setTitle("GUI");
		frmL.setBounds(100, 100, 825, 640);
		frmL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmL.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmL.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel(){
//			public void paintComponent(Graphics g) {  
//				Image img = null;
//				URL url = null;
//				try {
//					url = new URL("https://unsplash.it/640/825/?random");
//				} catch (MalformedURLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				try {
//					img = ImageIO.read(url);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}  
//				
//				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
//		
//			}
		};
		tabbedPane.addTab("Home", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewReleases = new JLabel("New Releases");
		lblNewReleases.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewReleases.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewReleases = new GridBagConstraints();
		gbc_lblNewReleases.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewReleases.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewReleases.gridx = 0;
		gbc_lblNewReleases.gridy = 1;
		panel_1.add(lblNewReleases, gbc_lblNewReleases);
		
		JLabel lblHighestRated = new JLabel("Highest rated");
		lblHighestRated.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHighestRated.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblHighestRated = new GridBagConstraints();
		gbc_lblHighestRated.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHighestRated.gridx = 1;
		gbc_lblHighestRated.gridy = 1;
		panel_1.add(lblHighestRated, gbc_lblHighestRated);
		
		JPanel panel = new JPanel(){
//			public void paintComponent(Graphics g) {  
//				Image img = null;
//				URL url = null;
//				try {
//					url = new URL("https://unsplash.it/640/825/?random");
//				} catch (MalformedURLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				try {
//					img = ImageIO.read(url);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}  
//				
//				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
//		
//			}
		};
		
		panel.setBorder(new EmptyBorder(2, 3, 2, 3));
		tabbedPane.addTab("Search", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		String defaultSearchStr = "Search Movies, People, TV Shows, etc.";
		
		txtSearchMoviesPeople = new JTextField();
		txtSearchMoviesPeople.setText(defaultSearchStr);
		txtSearchMoviesPeople.addActionListener(actionHandler);
		txtSearchMoviesPeople.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(txtSearchMoviesPeople.getText().equals(defaultSearchStr)){
					txtSearchMoviesPeople.setText("");
				}
            }
		});
		GridBagConstraints gbc_txtSearchMoviesPeople = new GridBagConstraints();
		gbc_txtSearchMoviesPeople.weightx = 81.0;
		gbc_txtSearchMoviesPeople.ipadx = 80;
		gbc_txtSearchMoviesPeople.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearchMoviesPeople.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearchMoviesPeople.gridwidth = 2;
		gbc_txtSearchMoviesPeople.gridx = 0;
		gbc_txtSearchMoviesPeople.gridy = 0;
		panel.add(txtSearchMoviesPeople, gbc_txtSearchMoviesPeople);
		txtSearchMoviesPeople.setColumns(10);
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(actionHandler);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(searchButton, gbc_btnNewButton_1);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane_1 = new GridBagConstraints();
		gbc_tabbedPane_1.ipady = 99;
		gbc_tabbedPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane_1.gridwidth = 3;
		gbc_tabbedPane_1.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_1.gridx = 0;
		gbc_tabbedPane_1.gridy = 1;
		panel.add(tabbedPane_1, gbc_tabbedPane_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_1.addTab("Movies", null, panel_3, null);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		tableMovie = new JTable();
		tableMovie.setToolTipText("");
		tableMovie.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tableMovie.getTableHeader().setReorderingAllowed(false);
		tableMovie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

            }
        });
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.ipady = 90;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		panel_3.add(tableMovie, gbc_table);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("People", null, panel_4, null);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		tablePeople = new JTable();
		tablePeople.setToolTipText("");
		tablePeople.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablePeople.getTableHeader().setReorderingAllowed(false);
		tablePeople.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
 
            }
        });
		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.fill = GridBagConstraints.BOTH;
		gbc_table_1.gridx = 0;
		gbc_table_1.gridy = 0;
		panel_4.add(tablePeople, gbc_table_1);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("TV Shows", null, panel_5, null);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		tableTv = new JTable();
		tableTv.setToolTipText("");
		tableTv.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tableTv.getTableHeader().setReorderingAllowed(false);
		tableTv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

            }
        });
		GridBagConstraints gbc_table_2 = new GridBagConstraints();
		gbc_table_2.fill = GridBagConstraints.BOTH;
		gbc_table_2.gridx = 0;
		gbc_table_2.gridy = 0;
		panel_5.add(tableTv, gbc_table_2);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EmptyBorder(2, 2, 2, 2));
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.gridheight = 0;
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridwidth = 3;
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 2;
		panel.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblTitle = new JLabel("Title");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.EAST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		panel_6.add(lblTitle, gbc_lblTitle);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_6.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblDirector = new JLabel("Director");
		GridBagConstraints gbc_lblDirector = new GridBagConstraints();
		gbc_lblDirector.anchor = GridBagConstraints.EAST;
		gbc_lblDirector.insets = new Insets(0, 0, 5, 5);
		gbc_lblDirector.gridx = 0;
		gbc_lblDirector.gridy = 1;
		panel_6.add(lblDirector, gbc_lblDirector);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel_6.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Release Date");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel_6.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panel_6.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.LIGHT_GRAY);
		panel_2.setBorder(new EmptyBorder(2, 4, 2, 4));
		tabbedPane.addTab("Log in", null, panel_2, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{48, 86, 0};
		gbl_panel_2.rowHeights = new int[]{20, 20, 23, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		panel_2.add(lblUsername, gbc_lblUsername);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 0;
		panel_2.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		panel_2.add(lblPassword, gbc_lblPassword);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 1;
		panel_2.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.ipadx = 78;
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panel_2.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Guest Session");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 3;
		panel_2.add(btnNewButton_1, gbc_btnNewButton_1);
	}

	private ActionListener actionHandler = new ActionListener() {
		
		public void actionPerformed(ActionEvent e){
			Object which = e.getSource();
			if(which == searchButton || which == txtSearchMoviesPeople){
				search.Multi(txtSearchMoviesPeople.getText());
			}
		}
		
	};
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
