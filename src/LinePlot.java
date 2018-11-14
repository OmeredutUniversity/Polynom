
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.data.Row;
import de.erichseifert.gral.graphics.Drawable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;


/**
 * This Class draw polynom on table between given range and his extreme point
 * @author omeredut
 */
public class LinePlot extends JFrame {

    private final double eps = 0.01;
    private double startRange;
    private double endRange;
    Polynom polynom;


    public LinePlot(Polynom p, double startRange, double endRange) {
        polynom = p;
        this.startRange = startRange;
        this.endRange = endRange;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        DataTable data = new DataTable(Double.class, Double.class);
        for (double x = startRange; x <= endRange; x += 0.25) {
            double y = p.f(x);
            data.add(x, y);
        }
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color colorPoint = new Color(0,0,0,0);
        Color colorLine = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderers(data).get(0).setColor(colorPoint);
        plot.getLineRenderers(data).get(0).setColor(colorLine);



        DataTable l = getExtremePoints();
        plot.add(l);
        plot.getPointRenderers(l).get(0).setColor(Color.RED);


    }





    private DataTable getExtremePoints(){
        DataTable extremePoints = new DataTable(Double.class, Double.class);
        Polynom pDerivative = (Polynom) polynom.derivative();
        double currentRange = startRange;
        while (currentRange <= endRange){
            if (Math.abs(pDerivative.f(currentRange)) < eps){
            		if (pDerivative.f(currentRange-eps)*pDerivative.f(currentRange+eps) < 0) {
            			extremePoints.add(currentRange, polynom.f(currentRange));
            		}
            }
            currentRange += eps/2;
        }
        return extremePoints;
    }






}
