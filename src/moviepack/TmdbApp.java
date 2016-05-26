package moviepack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import info.movito.themoviedbapi.TmdbApi;

/**
 * GUI for the tmdbApi.
 *
 * @author Nicholas Pahl
 *
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
     * Search class for searching strings.
     */
    private Search search;

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
    private JTextField passwordTextField;

    /**
     * text field for search.
     */
    private JTextField txtSearchMoviesPeople;

    /**
     * buttom for searching.
     */
    private JButton searchButton;

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
     * Label for clicked on search results.
     */
    private JLabel resultLabel;

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

    /**
     * Create the application.
     */
    public TmdbApp() {
        initialize();

        // User Defined

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
        GridBagLayout gblPanel1 = new GridBagLayout();
        gblPanel1.columnWidths = new int[] {0, 0, 0};
        gblPanel1.rowHeights = new int[] {0, 0, 0};
        gblPanel1.columnWeights = new double[] {1.0, 1.0, Double.MIN_VALUE};
        gblPanel1.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
        homePanel.setLayout(gblPanel1);

        JLabel lblNewReleases = new JLabel("New Releases");
        lblNewReleases.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewReleases.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbcLblNewReleases = new GridBagConstraints();
        gbcLblNewReleases.fill = GridBagConstraints.HORIZONTAL;
        gbcLblNewReleases.insets = new Insets(0, 0, 0, 5);
        gbcLblNewReleases.gridx = 0;
        gbcLblNewReleases.gridy = 1;
        homePanel.add(lblNewReleases, gbcLblNewReleases);

        JLabel lblHighestRated = new JLabel("Highest rated");
        lblHighestRated.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblHighestRated.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbcLblHighestRated = new GridBagConstraints();
        gbcLblHighestRated.fill = GridBagConstraints.HORIZONTAL;
        gbcLblHighestRated.gridx = 1;
        gbcLblHighestRated.gridy = 1;
        homePanel.add(lblHighestRated, gbcLblHighestRated);

        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(new EmptyBorder(2, 3, 2, 3));
        tabbedPane.addTab("Search", null, searchPanel, null);
        GridBagLayout gblPanel = new GridBagLayout();
        gblPanel.columnWidths = new int[] {0, 0, 0, 0};
        gblPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        gblPanel.columnWeights = new double[] {1.0, 0.0, 1.0,
                Double.MIN_VALUE};
        gblPanel.rowWeights = new double[] {0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        searchPanel.setLayout(gblPanel);

        String defaultSearchStr = "Search Movies, People, TV Shows, etc.";

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
        gbcTabbedPane1.ipady = 99;
        gbcTabbedPane1.insets = new Insets(0, 0, 5, 0);
        gbcTabbedPane1.gridwidth = 3;
        gbcTabbedPane1.fill = GridBagConstraints.BOTH;
        gbcTabbedPane1.gridx = 0;
        gbcTabbedPane1.gridy = 1;
        searchPanel.add(searchResultTabPane, gbcTabbedPane1);

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
                movieClicked();
            }
        });
        GridBagConstraints gbcTable = new GridBagConstraints();
        gbcTable.ipady = 90;
        gbcTable.fill = GridBagConstraints.BOTH;
        gbcTable.gridx = 0;
        gbcTable.gridy = 0;
        moviePanel.add(new JScrollPane(tableMovie), gbcTable);

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

        JPanel resultClickedPanel = new JPanel();
        resultClickedPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
        GridBagConstraints gbcPanel6 = new GridBagConstraints();
        gbcPanel6.gridheight = 0;
        gbcPanel6.fill = GridBagConstraints.BOTH;
        gbcPanel6.gridwidth = 3;
        gbcPanel6.insets = new Insets(0, 0, 5, 5);
        gbcPanel6.gridx = 0;
        gbcPanel6.gridy = 2;
        searchPanel.add(resultClickedPanel, gbcPanel6);
        GridBagLayout gblPanel6 = new GridBagLayout();
        gblPanel6.columnWidths = new int[] {0, 0, 0};
        gblPanel6.rowHeights = new int[] {0, 0, 0, 0, 0};
        gblPanel6.columnWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
        gblPanel6.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        resultClickedPanel.setLayout(gblPanel6);

        resultLabel = new JLabel();
        GridBagConstraints gbcLblTitle = new GridBagConstraints();
        gbcLblTitle.anchor = GridBagConstraints.WEST;
        gbcLblTitle.insets = new Insets(0, 0, 5, 5);
        gbcLblTitle.gridx = 0;
        gbcLblTitle.gridy = 0;
        resultClickedPanel.add(resultLabel, gbcLblTitle);

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

        passwordTextField = new JTextField();
        GridBagConstraints gbcTextField4 = new GridBagConstraints();
        gbcTextField4.anchor = GridBagConstraints.NORTHWEST;
        gbcTextField4.insets = new Insets(0, 0, 5, 0);
        gbcTextField4.gridx = 1;
        gbcTextField4.gridy = 1;
        loginPanel.add(passwordTextField, gbcTextField4);
        passwordTextField.setColumns(10);

        JButton loginButton = new JButton("Log in");
        GridBagConstraints gbcBtnNewButton = new GridBagConstraints();
        gbcBtnNewButton.insets = new Insets(0, 0, 5, 0);
        gbcBtnNewButton.ipadx = 78;
        gbcBtnNewButton.anchor = GridBagConstraints.NORTH;
        gbcBtnNewButton.gridwidth = 2;
        gbcBtnNewButton.gridx = 0;
        gbcBtnNewButton.gridy = 2;
        loginPanel.add(loginButton, gbcBtnNewButton);

        JButton guestButton = new JButton("Guest Session");
        GridBagConstraints gbcBtnNewButton_2 = new GridBagConstraints();
        gbcBtnNewButton_2.gridwidth = 2;
        gbcBtnNewButton_2.insets = new Insets(0, 0, 0, 5);
        gbcBtnNewButton_2.gridx = 0;
        gbcBtnNewButton_2.gridy = 3;
        loginPanel.add(guestButton, gbcBtnNewButton_2);
    }

    /**
     * Reacts to actions from searching.
     */
    private ActionListener actionHandler = new ActionListener() {

        public void actionPerformed(final ActionEvent e) {
            Object which = e.getSource();
            if (which == searchButton || which == txtSearchMoviesPeople) {
                search.stringSearch(txtSearchMoviesPeople.getText());
            }
        }

    };

    /**
     * When movie is clicked the resultLabel is filled with movie info.
     */
    private void movieClicked() {

        String resultStr = search.getMovieResults(
                movieModel.get(tableMovie.getSelectedRow()).getId());
        resultLabel.setText(resultStr);

    }

    /**
     * When person is clicked the resultLabel is filled with person info.
     */
    private void personClicked() {
        String resultStr = search.getPersonResults(
                peopleModel.get(tablePeople.getSelectedRow()).getId());
        resultLabel.setText(resultStr);
    }

    /**
     * When tv is clicked the resultLabel is filled with tv info.
     */
    private void tvClicked() {
        String resultStr = search
                .getTvResults(tvModel.get(tableTv.getSelectedRow()).getId());
        resultLabel.setText(resultStr);
    }
}
