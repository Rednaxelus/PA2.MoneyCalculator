
import presenter.CalculationDelegation;
import view.MainJFrame;
import model.Currency;
import view.APIExchangeRateLoader;
import view.ExchangeRateCalculator;


/*
 * For questions about licensing ask.
 */
/**
 *
 * @author Alex
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        MainJFrame mainJFrame = new MainJFrame(Currency.obtainCurrencyISOCodes());
        mainJFrame.setLocationRelativeTo(null);
        mainJFrame.setVisible(true);
        mainJFrame.addObserver(new CalculationDelegation(mainJFrame, new APIExchangeRateLoader(), new ExchangeRateCalculator()));

    }

}
