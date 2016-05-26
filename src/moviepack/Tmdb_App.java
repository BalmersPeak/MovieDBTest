package moviepack;
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
import java.util.List;
import java.util.ListIterator;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import java.awt.Button;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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
	private JTextField userNameTextField;
	private JTextField passwordTextField;
	private JTextField txtSearchMoviesPeople;
	private JButton searchButton;
	private JTable tableMovie;
	private JTable tablePeople;
	private JTable tableTv;
	
	private JLabel resultLabel;


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
		
		JPanel homePanel = new JPanel();
		tabbedPane.addTab("Home", null, homePanel, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		homePanel.setLayout(gbl_panel_1);
		
		JLabel lblNewReleases = new JLabel("New Releases");
		lblNewReleases.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewReleases.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewReleases = new GridBagConstraints();
		gbc_lblNewReleases.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewReleases.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewReleases.gridx = 0;
		gbc_lblNewReleases.gridy = 1;
		homePanel.add(lblNewReleases, gbc_lblNewReleases);
		
		JLabel lblHighestRated = new JLabel("Highest rated");
		lblHighestRated.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHighestRated.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblHighestRated = new GridBagConstraints();
		gbc_lblHighestRated.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHighestRated.gridx = 1;
		gbc_lblHighestRated.gridy = 1;
		homePanel.add(lblHighestRated, gbc_lblHighestRated);
		
		JPanel searchPanel = new JPanel();		
		searchPanel.setBorder(new EmptyBorder(2, 3, 2, 3));
		tabbedPane.addTab("Search", null, searchPanel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		searchPanel.setLayout(gbl_panel);
		
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
		searchPanel.add(txtSearchMoviesPeople, gbc_txtSearchMoviesPeople);
		txtSearchMoviesPeople.setColumns(10);
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(actionHandler);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		searchPanel.add(searchButton, gbc_btnNewButton_1);
		
		JTabbedPane searchResultTabPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane_1 = new GridBagConstraints();
		gbc_tabbedPane_1.ipady = 99;
		gbc_tabbedPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane_1.gridwidth = 3;
		gbc_tabbedPane_1.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_1.gridx = 0;
		gbc_tabbedPane_1.gridy = 1;
		searchPanel.add(searchResultTabPane, gbc_tabbedPane_1);
		
		JPanel moviePanel = new JPanel();
		searchResultTabPane.addTab("Movies", null, moviePanel, null);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		moviePanel.setLayout(gbl_panel_3);
		
		tableMovie = new JTable();
		tableMovie.setToolTipText("");
		tableMovie.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tableMovie.getTableHeader().setReorderingAllowed(false);
		tableMovie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	movieClicked();
            }
        });
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.ipady = 90;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		moviePanel.add(new JScrollPane(tableMovie), gbc_table);
		
		JPanel peoplePanel = new JPanel();
		searchResultTabPane.addTab("People", null, peoplePanel, null);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		peoplePanel.setLayout(gbl_panel_4);
		
		tablePeople = new JTable();
		tablePeople.setToolTipText("");
		tablePeople.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablePeople.getTableHeader().setReorderingAllowed(false);
		tablePeople.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	personClicked();
            }
        });
		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.fill = GridBagConstraints.BOTH;
		gbc_table_1.gridx = 0;
		gbc_table_1.gridy = 0;
		peoplePanel.add(new JScrollPane(tablePeople), gbc_table_1);
		
		JPanel tvPanel = new JPanel();
		searchResultTabPane.addTab("TV Shows", null, tvPanel, null);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		tvPanel.setLayout(gbl_panel_5);
		
		tableTv = new JTable();
		tableTv.setToolTipText("");
		tableTv.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tableTv.getTableHeader().setReorderingAllowed(false);
		tableTv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	tvClicked();
            }
        });
		GridBagConstraints gbc_table_2 = new GridBagConstraints();
		gbc_table_2.fill = GridBagConstraints.BOTH;
		gbc_table_2.gridx = 0;
		gbc_table_2.gridy = 0;
		tvPanel.add(new JScrollPane(tableTv), gbc_table_2);
		
		JPanel resultClickedPanel = new JPanel();
		resultClickedPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.gridheight = 0;
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridwidth = 3;
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 2;
		searchPanel.add(resultClickedPanel, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		resultClickedPanel.setLayout(gbl_panel_6);
		
		resultLabel = new JLabel();
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		resultClickedPanel.add(resultLabel, gbc_lblTitle);
			

		JPanel loginPanel = new JPanel();
		loginPanel.setForeground(Color.LIGHT_GRAY);
		loginPanel.setBorder(new EmptyBorder(2, 4, 2, 4));
		tabbedPane.addTab("Log in", null, loginPanel, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{48, 86, 0};
		gbl_panel_2.rowHeights = new int[]{20, 20, 23, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		loginPanel.setLayout(gbl_panel_2);
		
		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		loginPanel.add(lblUsername, gbc_lblUsername);
		
		userNameTextField = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 0;
		loginPanel.add(userNameTextField, gbc_textField_3);
		userNameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		loginPanel.add(lblPassword, gbc_lblPassword);
		
		passwordTextField = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 1;
		loginPanel.add(passwordTextField, gbc_textField_4);
		passwordTextField.setColumns(10);
		
		JButton loginButton = new JButton("Log in");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.ipadx = 78;
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		loginPanel.add(loginButton, gbc_btnNewButton);
		
		JButton guestButton = new JButton("Guest Session");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridwidth = 2;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 3;
		loginPanel.add(guestButton, gbc_btnNewButton_2);
	}

	private ActionListener actionHandler = new ActionListener() {
		
		public void actionPerformed(ActionEvent e){
			Object which = e.getSource();
			if(which == searchButton || which == txtSearchMoviesPeople){
				search.stringSearch(txtSearchMoviesPeople.getText());
			}
		}
		
	};
	
	private void movieClicked(){
		
		String resultStr = search.getMovieResults(movieModel.get(tableMovie.getSelectedRow()).getId());
		resultLabel.setText(resultStr);
		
	}
	
	private void personClicked(){
		String resultStr = search.getPersonResults(peopleModel.get(tablePeople.getSelectedRow()).getId());
		resultLabel.setText(resultStr);
	}
	
	private void tvClicked(){
		String resultStr = search.getTvResults(tvModel.get(tableTv.getSelectedRow()).getId());
		resultLabel.setText(resultStr);
	}
}
