package bean;

import repo.MahasiswaManager;
import repo.StatusMahasiswaManager;
import software.xdev.chartjs.model.charts.BarChart;
import software.xdev.chartjs.model.charts.LineChart;
import software.xdev.chartjs.model.charts.PieChart;
import software.xdev.chartjs.model.color.Color;
import software.xdev.chartjs.model.data.BarData;
import software.xdev.chartjs.model.data.LineData;
import software.xdev.chartjs.model.data.PieData;
import software.xdev.chartjs.model.dataset.BarDataset;
import software.xdev.chartjs.model.dataset.LineDataset;
import software.xdev.chartjs.model.dataset.PieDataset;
import software.xdev.chartjs.model.options.BarOptions;
import software.xdev.chartjs.model.options.LineOptions;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class ChartBean implements Serializable {

    String pieModel;
    String lineModelMahasiswaAktifByAngkatan;

    String stackedBarModelMahasiswaAktifByStatusWithAngkatan;

    @PostConstruct
    public void init() {
        pieModel = "";
        lineModelMahasiswaAktifByAngkatan = "";
        stackedBarModelMahasiswaAktifByStatusWithAngkatan = "";
    }

    @EJB
    MahasiswaManager mahasiswaManager;

    @EJB
    StatusMahasiswaManager statusMahasiswaManager;

    public void generateChartData() {
        createPieModel();
    }

    public void createPieModel() {
        Long ti = mahasiswaManager.findJumlahMahasiswaByKodeProdi("TI");
        Long si = mahasiswaManager.findJumlahMahasiswaByKodeProdi("SI");
        PieChart pieChart = new PieChart();
        PieData pieData = new PieData();

        PieDataset pieDataset = new PieDataset();
        pieDataset.setData(ti, si);
        pieDataset.setLabel("Jumlah siswa");
        pieDataset.addBackgroundColors(new Color(255, 19, 132), new Color(54, 162, 235));

        pieData.setLabels("TI", "SI");
        pieData.addDataset(pieDataset);

        pieChart.setData(pieData);
        pieModel = pieChart.toJson();
        //System.out.println(pieModel);
    }

    public void generateChartJumlahMahasiswaAktifByStatusPerAngkatan() {
        //List<Mahasiswa> mahasiswas = mahasiswaManager.findAllMahasiswaWithStatus();
        String[] status = new String[]{"AKTIF", "NONAKTIF", "DROPOUT", "ALUMNI", "CUTI"};
        List<Integer> tahuns = mahasiswaManager.findAllDistinctAngkatan();
        List<String> tahunsString = new ArrayList<>();
        for (Integer i : tahuns) {
            if (i == null)
                tahunsString.add("null");
            else
                tahunsString.add(i.toString());
        }
        Map<String, List<Long>> maps = new HashMap<>();

        for (String s : status) {
            maps.put(s, new ArrayList<>());
            List<Object[]> objects = mahasiswaManager.findAllMahasiswaByStatusWithAngkatan(s);
            if (objects != null) {
                for (String tahun : tahunsString) {
                    Boolean ketemu = false;
                    Long jumlah = 0L;
                    for (Object[] object : objects) {
                        if (tahun.equals(String.valueOf(object[0]))) {
                            jumlah = (Long) object[1];
                            ketemu = true;
                            break;
                        }
                    }
                    if (ketemu)
                        maps.get(s).add(jumlah);
                    else
                        maps.get(s).add(0L);
                }
            }
        }


        BarChart bc = new BarChart();

        BarData bd = new BarData();
        BarDataset bds;
        int r = 100;
        int g = 50;
        int b = 75;
        for (String s : status) {
            bds = new BarDataset();
            bds.setLabel(s);
            bds.setData(maps.get(s).toArray(new Long[0]));
            bds.setBackgroundColor(new Color(r, g, b));
            r = (r + 11) % 255;
            g = (g + 48) % 255;
            b = (b + 23) % 255;
            bd.addDataset(bds);
        }

        bd.addLabels(tahunsString.toArray(new String[0]));
        BarOptions bo = new BarOptions()
                .setResponsive(true)
                .setMaintainAspectRatio(false)
                //.setPlugins(new Plugins()
                //        .setTitle(new Title()
                //                .setDisplay(true)
                //                .setText("Bar Chart - Stacked")));
        ;

        bc.setData(bd).setOptions(bo);

        stackedBarModelMahasiswaAktifByStatusWithAngkatan = bc.toJson();
        //System.out.println(stackedBarModelMahasiswaAktifByStatusWithAngkatan);

    }

    public void generateChartJumlahMahasiswaAktifByAngkatan() {
        List<Object[]> objects = mahasiswaManager.findCountAllMahasiswaAktifByTahunAngkatan();
        if (objects != null) {
            String[] tahunAngkatans = new String[objects.size()];
            Long[] counts = new Long[objects.size()];

            for (int i = 0; i < objects.size(); i++) {
                tahunAngkatans[i] = String.valueOf(objects.get(i)[0]);
                counts[i] = (Long) objects.get(i)[1];
            }

            //buat objek linechart
            LineChart lc = new LineChart();

            //buat objek linedata
            LineData ld = new LineData();

            //buat objek linedataset
            LineDataset lds = new LineDataset();

            //setting dataset
            lds.setData(counts);

            lds.setLabel("Jumlah Mahasiswa");
            lds.setBorderColor(new Color(75, 192, 192));
            lds.setLineTension(0.1f);
            //lds.setFill(new Fill<Boolean>(false));

            //setting linedata
            ld.addDataset(lds);
            ld.setLabels(tahunAngkatans);

            //setting linechart
            lc.setData(ld);

            //setting lineoptions
            lc.setOptions(new LineOptions()
                    .setResponsive(true)
                    .setMaintainAspectRatio(false)
            );

            lineModelMahasiswaAktifByAngkatan = lc.toJson();

        }
    }

    public String getPieModel() {
        return pieModel;
    }

    public void setPieModel(String pieModel) {
        this.pieModel = pieModel;
    }

    public String getLineModelMahasiswaAktifByAngkatan() {
        return lineModelMahasiswaAktifByAngkatan;
    }

    public void setLineModelMahasiswaAktifByAngkatan(String lineModelMahasiswaAktifByAngkatan) {
        this.lineModelMahasiswaAktifByAngkatan = lineModelMahasiswaAktifByAngkatan;
    }

    public String getStackedBarModelMahasiswaAktifByStatusWithAngkatan() {
        return stackedBarModelMahasiswaAktifByStatusWithAngkatan;
    }

    public void setStackedBarModelMahasiswaAktifByStatusWithAngkatan(String stackedBarModelMahasiswaAktifByStatusWithAngkatan) {
        this.stackedBarModelMahasiswaAktifByStatusWithAngkatan = stackedBarModelMahasiswaAktifByStatusWithAngkatan;
    }
}
