package moviepack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.people.PersonPeople;

/**
 * GUI for the tmdbApi.
 *
 * @author Nicholas Pahl
 */
public class TmdbApp {

    /**
     * TmdbApi gives access to all tmdbApi classes.
     */
    private TmdbApi tmdbApi;

    /**
     * Movie model to enter data into the table.
     */
    private MovieModel movieModel;

    /**
     * People model to enter data into the table.
     */
    private PeopleModel peopleModel;

    /**
     * Tv model to enter data into the table.
     */

    private TvModel tvModel;

    /**
     * Movie model to enter keyword data into the table.
     */
    private MovieModel keywordModel;

    /**
     * Search class for searching strings.
     */
    private Search search;
    
    /**
     * Login class for logging in.
     */
    private Login login;

    /**
     * Keyword class for searching keywords.
     */
    private KeywordMatch keyword;

    /**
     * Frame of Gui.
     */
    private JFrame frmL;

    /**
     * text field for username.
     */
    private JTextField userNameTextField;

    /**
     * text field for password.
     */
    private JPasswordField passwordTextField;

    /**
     * text field for search.
     */
    private JTextField txtSearchMoviesPeople;

    /**
     * button for searching.
     */
    private JButton searchButton;
    
    /**
     * button for logging in.
     */
    private JButton loginButton;
    
    /**
     * button for starting a guest session.
     */
    private JButton guestButton;

    /**
     * table for movies.
     */
    private JTable tableMovie;

    /**
     * table for people.
     */
    private JTable tablePeople;

    /**
     * table for tv series.
     */
    private JTable tableTv;

    /**
     * table for movies.
     */
    private JTable tableKeyword;

    /**
     * Label for clicked on search results.
     */
    private JLabel resultLabel;

    /**
     * Label for clicked on searched results picture.
     */
    private JLabel resultsPicLabel;

    /**
     * Url for pictures of results.
     */
    private URL resultsUrl = null;

    /**
     * Launch the application.
     *
     * @param args
     *            String of arguments.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TmdbApp window = new TmdbApp();
                    window.frmL.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

// Check:OFF: MagicNumber

    /**
     * Create the application.
     */
    public TmdbApp() {


        // User Defined

        tmdbApi = new TmdbApi("811ffa781385ed56e1ec64c193eb93f4");

        movieModel = new MovieModel();
        peopleModel = new PeopleModel();
        tvModel = new TvModel();
        keywordModel = new MovieModel();

        search = new Search(tmdbApi, movieModel, peopleModel, tvModel);
        keyword = new KeywordMatch(tmdbApi, keywordModel);
        
        login = new Login(tmdbApi);

        initialize();

        tableMovie.setModel(movieModel);
        tablePeople.setModel(peopleModel);
        tableTv.setModel(tvModel);
        tableKeyword.setModel(keywordModel);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmL = new JFrame();
        frmL.setTitle("ReelMatch");
        frmL.setBounds(100, 100, 827, 1000);
        frmL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmL.getContentPane().setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frmL.getContentPane().add(tabbedPane, BorderLayout.NORTH);

        setupHomeTab(tabbedPane);

        setupSearchTab(tabbedPane);

        setupLoginTab(tabbedPane);

        resultsPicLabel = new JLabel();
        resultsPicLabel.setPreferredSize(new Dimension(185, 280));
        resultsPicLabel.setVisible(true);
        frmL.add(resultsPicLabel, BorderLayout.WEST);

        resultLabel = new JLabel();
        frmL.add(resultLabel, BorderLayout.CENTER);

//        frmL.pack();
    }

    /**
     * Sets up the Home tab.
     * @param tabbedPane
     *            the tabbedPane to add the home tab to.
     */
    private void setupHomeTab(final JTabbedPane tabbedPane) {
        JPanel homePanel = new JPanel();
        tabbedPane.addTab("Home", null, homePanel, null);
        GridBagLayout gblPanel1 = new GridBagLayout();
        gblPanel1.columnWidths = new int[] {0, 0, 0};
        gblPanel1.rowHeights = new int[] {0, 0, 0, 0};
        gblPanel1.columnWeights = new double[] {0.0, 0.0, 0.0};
        gblPanel1.rowWeights = new double[] {0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        homePanel.setLayout(gblPanel1);

        JLabel lblNewReleases = new JLabel("New Releases");
        lblNewReleases.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewReleases.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbcLblNewReleases = new GridBagConstraints();
        gbcLblNewReleases.insets = new Insets(0, 0, 5, 0);
        gbcLblNewReleases.gridwidth = 3;
        gbcLblNewReleases.gridx = 0;
        gbcLblNewReleases.gridy = 0;
        homePanel.add(lblNewReleases, gbcLblNewReleases);

        search.getPopularMovies();

        ArrayList<MovieDb> movieList = new ArrayList<MovieDb>(
                search.getPopularMovies());

        JLabel lblPic = new JLabel();
        try {
            URL url = new URL(search.getMultiImageUrl(movieList.get(0)));
            ImageIcon icon = new ImageIcon(url);
            lblPic.setIcon(icon);
        } catch (IOException e) {

        }
        lblPic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                movieClicked(movieList.get(0));
            }
        });

        GridBagConstraints gbcLblPic = new GridBagConstraints();
        gbcLblPic.fill = SwingConstants.CENTER;
        gbcLblPic.insets = new Insets(0, 0, 5, 5);
        gbcLblPic.gridx = 0;
        gbcLblPic.gridy = 1;
        homePanel.add(lblPic, gbcLblPic);

        JLabel lblPic1 = new JLabel();
        try {
            URL url = new URL(search.getMultiImageUrl(movieList.get(1)));
            ImageIcon icon = new ImageIcon(url);
            lblPic1.setIcon(icon);
        } catch (IOException e) {

        }
        lblPic1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                movieClicked(movieList.get(1));
            }
        });

        GridBagConstraints gbcLblPic1 = new GridBagConstraints();
        gbcLblPic1.fill = SwingConstants.CENTER;
        gbcLblPic1.insets = new Insets(0, 0, 5, 5);
        gbcLblPic1.gridx = 1;
        gbcLblPic1.gridy = 1;
        homePanel.add(lblPic1, gbcLblPic1);

        JLabel lblPic2 = new JLabel();
        try {
            URL url = new URL(search.getMultiImageUrl(movieList.get(2)));
            ImageIcon icon = new ImageIcon(url);
            lblPic2.setIcon(icon);
        } catch (IOException e) {

        }
        lblPic2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                movieClicked(movieList.get(2));
            }
        });

        GridBagConstraints gbcLblPic2 = new GridBagConstraints();
        gbcLblPic2.insets = new Insets(0, 0, 5, 0);
        gbcLblPic2.gridx = 2;
        gbcLblPic2.gridy = 1;
        homePanel.add(lblPic2, gbcLblPic2);

        JLabel lblPic3 = new JLabel();
        try {
            URL url = new URL(search.getMultiImageUrl(movieList.get(3)));
            ImageIcon icon = new ImageIcon(url);
            lblPic3.setIcon(icon);
        } catch (IOException e) {

        }
        lblPic3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                movieClicked(movieList.get(3));
            }
        });

        GridBagConstraints gbcLblPic3 = new GridBagConstraints();
        gbcLblPic3.insets = new Insets(0, 0, 0, 5);
        gbcLblPic3.gridx = 0;
        gbcLblPic3.gridy = 2;
        homePanel.add(lblPic3, gbcLblPic3);

        JLabel lblPic4 = new JLabel();
        try {
            URL url = new URL(search.getMultiImageUrl(movieList.get(4)));
            ImageIcon icon = new ImageIcon(url);
            lblPic4.setIcon(icon);
        } catch (IOException e) {

        }
        lblPic4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                movieClicked(movieList.get(4));
            }
        });

        GridBagConstraints gbcLblPic4 = new GridBagConstraints();
        gbcLblPic4.insets = new Insets(0, 0, 0, 5);
        gbcLblPic4.gridx = 1;
        gbcLblPic4.gridy = 2;
        homePanel.add(lblPic4, gbcLblPic4);

        JLabel lblPic5 = new JLabel();
        try {
            URL url = new URL(search.getMultiImageUrl(movieList.get(5)));
            ImageIcon icon = new ImageIcon(url);
            lblPic5.setIcon(icon);
        } catch (IOException e) {

        }
        lblPic5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                movieClicked(movieList.get(5));
            }
        });

        GridBagConstraints gbcLblPic5 = new GridBagConstraints();
        gbcLblPic5.gridx = 2;
        gbcLblPic5.gridy = 2;
        homePanel.add(lblPic5, gbcLblPic5);
    }

    /**
     * Sets up the Search tab.
     * @param tabbedPane
     *            the tabbedPane to add the search tab to.
     */
    private void setupSearchTab(final JTabbedPane tabbedPane) {
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(new EmptyBorder(2, 3, 2, 3));
        tabbedPane.addTab("Search", null, searchPanel, null);
        GridBagLayout gblPanel = new GridBagLayout();
        gblPanel.columnWidths = new int[] {0, 0, 0, 0};
        gblPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        gblPanel.columnWeights = new double[] {1.0, 0.0, 1.0, Double.MIN_VALUE};
        gblPanel.rowWeights = new double[] {0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        searchPanel.setLayout(gblPanel);

        String defaultSearchStr = "Search Movies, People, TV Shows, "
                + "and Keyword(separated by commas)";
        txtSearchMoviesPeople = new JTextField();
        txtSearchMoviesPeople.setText(defaultSearchStr);
        txtSearchMoviesPeople.addActionListener(actionHandler);
        txtSearchMoviesPeople.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (txtSearchMoviesPeople.getText().equals(defaultSearchStr)) {
                    txtSearchMoviesPeople.setText("");
                }
            }
        });
        GridBagConstraints gbcTxtSearchMoviesPeople = new GridBagConstraints();
        gbcTxtSearchMoviesPeople.weightx = 81.0;
        gbcTxtSearchMoviesPeople.ipadx = 80;
        gbcTxtSearchMoviesPeople.insets = new Insets(0, 0, 5, 5);
        gbcTxtSearchMoviesPeople.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtSearchMoviesPeople.gridwidth = 2;
        gbcTxtSearchMoviesPeople.gridx = 0;
        gbcTxtSearchMoviesPeople.gridy = 0;
        searchPanel.add(txtSearchMoviesPeople, gbcTxtSearchMoviesPeople);
        txtSearchMoviesPeople.setColumns(10);

        searchButton = new JButton("Search");
        searchButton.addActionListener(actionHandler);
        GridBagConstraints gbcBtnNewButton1 = new GridBagConstraints();
        gbcBtnNewButton1.insets = new Insets(0, 0, 5, 0);
        gbcBtnNewButton1.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnNewButton1.gridx = 2;
        gbcBtnNewButton1.gridy = 0;
        searchPanel.add(searchButton, gbcBtnNewButton1);

        JTabbedPane searchResultTabPane = new JTabbedPane(JTabbedPane.TOP);
        GridBagConstraints gbcTabbedPane1 = new GridBagConstraints();
        gbcTabbedPane1.ipady = 50;
        gbcTabbedPane1.insets = new Insets(0, 0, 5, 0);
        gbcTabbedPane1.gridwidth = 3;
        gbcTabbedPane1.fill = GridBagConstraints.BOTH;
        gbcTabbedPane1.gridx = 0;
        gbcTabbedPane1.gridy = 1;
        searchPanel.add(searchResultTabPane, gbcTabbedPane1);

        setupSearchMovieTab(searchResultTabPane);
        setupSearchPeopleTab(searchResultTabPane);
        setupSearchTvTab(searchResultTabPane);
        setupSearchKeywordsTab(searchResultTabPane);


    }

    /**
     * Sets up the movie tab in the search tab.
     * @param searchResultTabPane
     *            tab to but the movie search results.
     */
    private void setupSearchMovieTab(final JTabbedPane searchResultTabPane) {
        JPanel moviePanel = new JPanel();
        searchResultTabPane.addTab("Movies", null, moviePanel, null);
        GridBagLayout gblPanel3 = new GridBagLayout();
        gblPanel3.columnWidths = new int[] {0, 0};
        gblPanel3.rowHeights = new int[] {0, 0};
        gblPanel3.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gblPanel3.rowWeights = new double[] {1.0, Double.MIN_VALUE};
        moviePanel.setLayout(gblPanel3);

        tableMovie = new JTable();
        tableMovie.setToolTipText("");
        tableMovie.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableMovie.getTableHeader().setReorderingAllowed(false);
        tableMovie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                movieClicked(movieModel.get(tableMovie.getSelectedRow()));
            }
        });
        GridBagConstraints gbcTable = new GridBagConstraints();
        gbcTable.ipady = 90;
        gbcTable.fill = GridBagConstraints.BOTH;
        gbcTable.gridx = 0;
        gbcTable.gridy = 0;
        moviePanel.add(new JScrollPane(tableMovie), gbcTable);

    }

    /**
     * Sets up the people tab in the search tab.
     * @param searchResultTabPane
     *            tab to but the people search results.
     */
    private void setupSearchPeopleTab(final JTabbedPane searchResultTabPane) {
        JPanel peoplePanel = new JPanel();
        searchResultTabPane.addTab("People", null, peoplePanel, null);
        GridBagLayout gblPanel4 = new GridBagLayout();
        gblPanel4.columnWidths = new int[] {0, 0};
        gblPanel4.rowHeights = new int[] {0, 0};
        gblPanel4.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gblPanel4.rowWeights = new double[] {1.0, Double.MIN_VALUE};
        peoplePanel.setLayout(gblPanel4);

        tablePeople = new JTable();
        tablePeople.setToolTipText("");
        tablePeople.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablePeople.getTableHeader().setReorderingAllowed(false);
        tablePeople.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                personClicked();
            }
        });
        GridBagConstraints gbcTable1 = new GridBagConstraints();
        gbcTable1.fill = GridBagConstraints.BOTH;
        gbcTable1.gridx = 0;
        gbcTable1.gridy = 0;
        peoplePanel.add(new JScrollPane(tablePeople), gbcTable1);
    }

    /**
     * Sets up the tv tab in the search tab.
     * @param searchResultTabPane
     *            tab to but the tv search results.
     */
    private void setupSearchTvTab(final JTabbedPane searchResultTabPane) {
        JPanel tvPanel = new JPanel();
        searchResultTabPane.addTab("TV Shows", null, tvPanel, null);
        GridBagLayout gblPanel5 = new GridBagLayout();
        gblPanel5.columnWidths = new int[] {0, 0};
        gblPanel5.rowHeights = new int[] {0, 0};
        gblPanel5.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gblPanel5.rowWeights = new double[] {1.0, Double.MIN_VALUE};
        tvPanel.setLayout(gblPanel5);

        tableTv = new JTable();
        tableTv.setToolTipText("");
        tableTv.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableTv.getTableHeader().setReorderingAllowed(false);
        tableTv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                tvClicked();
            }
        });
        GridBagConstraints gbcTable2 = new GridBagConstraints();
        gbcTable2.fill = GridBagConstraints.BOTH;
        gbcTable2.gridx = 0;
        gbcTable2.gridy = 0;
        tvPanel.add(new JScrollPane(tableTv), gbcTable2);
    }

    /**
     * Sets up the keywords tab in the search tab.
     * @param searchResultTabPane
     *            tab to but the keywords search results.
     */
    private void setupSearchKeywordsTab(final JTabbedPane searchResultTabPane) {
        JPanel keywordsPanel = new JPanel();
        searchResultTabPane.addTab("Keywords", null, keywordsPanel, null);
        GridBagLayout gblPanel3 = new GridBagLayout();
        gblPanel3.columnWidths = new int[] {0, 0};
        gblPanel3.rowHeights = new int[] {0, 0};
        gblPanel3.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gblPanel3.rowWeights = new double[] {1.0, Double.MIN_VALUE};
        keywordsPanel.setLayout(gblPanel3);

        tableKeyword = new JTable();
        tableKeyword.setToolTipText("");
        tableKeyword.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableKeyword.getTableHeader().setReorderingAllowed(false);
        tableKeyword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                keywordClicked();
            }
        });
        GridBagConstraints gbcTable = new GridBagConstraints();
        gbcTable.ipady = 90;
        gbcTable.fill = GridBagConstraints.BOTH;
        gbcTable.gridx = 0;
        gbcTable.gridy = 0;
        keywordsPanel.add(new JScrollPane(tableKeyword), gbcTable);

    }

    /**
     * Sets up the Login tab.
     * @param tabbedPane
     *            the tabbedPane to add the Login tab to.
     */
    private void setupLoginTab(final JTabbedPane tabbedPane) {
        JPanel loginPanel = new JPanel();
        loginPanel.setForeground(Color.LIGHT_GRAY);
        loginPanel.setBorder(new EmptyBorder(2, 4, 2, 4));
        tabbedPane.addTab("Log in", null, loginPanel, null);
        GridBagLayout gblPanel2 = new GridBagLayout();
        gblPanel2.columnWidths = new int[] {48, 86, 0};
        gblPanel2.rowHeights = new int[] {20, 20, 23, 0, 0};
        gblPanel2.columnWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
        gblPanel2.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        loginPanel.setLayout(gblPanel2);

        JLabel lblUsername = new JLabel("Username");
        GridBagConstraints gbcLblUsername = new GridBagConstraints();
        gbcLblUsername.anchor = GridBagConstraints.WEST;
        gbcLblUsername.insets = new Insets(0, 0, 5, 5);
        gbcLblUsername.gridx = 0;
        gbcLblUsername.gridy = 0;
        loginPanel.add(lblUsername, gbcLblUsername);

        userNameTextField = new JTextField();
        GridBagConstraints gbcTextField3 = new GridBagConstraints();
        gbcTextField3.anchor = GridBagConstraints.NORTHWEST;
        gbcTextField3.insets = new Insets(0, 0, 5, 0);
        gbcTextField3.gridx = 1;
        gbcTextField3.gridy = 0;
        loginPanel.add(userNameTextField, gbcTextField3);
        userNameTextField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        GridBagConstraints gbcLblPassword = new GridBagConstraints();
        gbcLblPassword.anchor = GridBagConstraints.EAST;
        gbcLblPassword.insets = new Insets(0, 0, 5, 5);
        gbcLblPassword.gridx = 0;
        gbcLblPassword.gridy = 1;
        loginPanel.add(lblPassword, gbcLblPassword);

        passwordTextField = new JPasswordField();
        passwordTextField.setFont(new Font("Dialog", Font.PLAIN, 12));
        passwordTextField.setEchoChar('*');
        passwordTextField.addActionListener(actionHandler);
        GridBagConstraints gbcTextField4 = new GridBagConstraints();
        gbcTextField4.anchor = GridBagConstraints.NORTHWEST;
        gbcTextField4.insets = new Insets(0, 0, 5, 0);
        gbcTextField4.gridx = 1;
        gbcTextField4.gridy = 1;
        loginPanel.add(passwordTextField, gbcTextField4);
        passwordTextField.setColumns(10);

        loginButton = new JButton("  Log in  ");
        GridBagConstraints gbcBtnNewButton = new GridBagConstraints();
        loginButton.addActionListener(actionHandler);
        gbcBtnNewButton.insets = new Insets(0, 0, 5, 0);
        gbcBtnNewButton.ipadx = 95;
        gbcBtnNewButton.anchor = GridBagConstraints.NORTH;
        gbcBtnNewButton.gridwidth = 2;
        gbcBtnNewButton.gridx = 0;
        gbcBtnNewButton.gridy = 2;
        loginPanel.add(loginButton, gbcBtnNewButton);

        guestButton = new JButton("Guest Session");
        GridBagConstraints gbcBtnNewButton2 = new GridBagConstraints();
        guestButton.addActionListener(actionHandler);
        gbcBtnNewButton2.anchor = GridBagConstraints.NORTH;
        gbcBtnNewButton2.ipadx = 59;
        gbcBtnNewButton2.gridwidth = 2;
        gbcBtnNewButton2.insets = new Insets(0, 0, 5, 0);
        gbcBtnNewButton2.gridx = 0;
        gbcBtnNewButton2.gridy = 3;
        loginPanel.add(guestButton, gbcBtnNewButton2);
    }

//Check:ON: MagicNumber

    /**
     * Reacts to actions from searching.
     */
    private ActionListener actionHandler = new ActionListener() {

        public void actionPerformed(final ActionEvent e) {
            Object which = e.getSource();
            if (which == searchButton || which == txtSearchMoviesPeople) {
                search.stringSearch(txtSearchMoviesPeople.getText());
                keyword.searchKeyword(txtSearchMoviesPeople.getText());
            }
            if (which == loginButton || which == passwordTextField) {
            	login.getSessionToken(userNameTextField.getText(), passwordTextField.getPassword());

            }
            if (which == guestButton) {
            	login.getSessionToken();
            	
            }
       }	
        

    };

    /**
     * When movie is clicked the resultLabel is filled with movie info.
     * @param movie Movie that is clicked.
     */
    private void movieClicked(final MovieDb movie) {

        String resultStr = search.getMovieResults(movie.getId());
        resultLabel.setText(resultStr);

        try {
            resultsUrl = new URL(search.getMultiImageUrl(movie));
        } catch (IOException e) {

        }
        ImageIcon icon = new ImageIcon(resultsUrl);
        resultsPicLabel.setVisible(true);
        resultsPicLabel.setIcon(icon);
    }

    /**
     * When person is clicked the resultLabel is filled with person info.
     */
    private void personClicked() {
        String resultStr = search.getPersonResults(
                peopleModel.get(tablePeople.getSelectedRow()).getId());
        resultLabel.setText(resultStr);

        PersonPeople p = search
                .getPersonPeople(peopleModel.get(tablePeople.getSelectedRow()));
        try {
            resultsUrl = new URL(search.getMultiImageUrl(p));
        } catch (IOException e) {

        }
        ImageIcon icon = new ImageIcon(resultsUrl);
        resultsPicLabel.setVisible(true);
        resultsPicLabel.setIcon(icon);
    }

    /**
     * When tv is clicked the resultLabel is filled with tv info.
     */
    private void tvClicked() {
        String resultStr = search
                .getTvResults(tvModel.get(tableTv.getSelectedRow()).getId());
        resultLabel.setText(resultStr);

        try {
            resultsUrl = new URL(search
                    .getMultiImageUrl(tvModel.get(tableTv.getSelectedRow())));
        } catch (IOException e) {

        }
        ImageIcon icon = new ImageIcon(resultsUrl);
        resultsPicLabel.setVisible(true);
        resultsPicLabel.setIcon(icon);
    }

    /**
     * When keyword movie is clicked the resultLabel is filled with movie info.
     */
    private void keywordClicked() {
        String resultStr = search.getMovieResults(
                keywordModel.get(tableKeyword.getSelectedRow()).getId());
        resultLabel.setText(resultStr);

        try {
            resultsUrl = new URL(search.getMultiImageUrl(
                    keywordModel.get(tableKeyword.getSelectedRow())));
        } catch (IOException e) {

        }
        ImageIcon icon = new ImageIcon(resultsUrl);
        resultsPicLabel.setVisible(true);
        resultsPicLabel.setIcon(icon);
    }
}
