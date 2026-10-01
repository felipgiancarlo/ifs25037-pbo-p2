package adapter.presenter;

import domain.entity.Activity;
import java.util.List;

public class ActivityPresenter {

    public void printActivityList(List<Activity> activities) {
        System.out.println("Daftar Kegiatan:");
        if (activities.isEmpty()) {
            System.out.println("- Data kegiatan belum tersedia!");
        } else {
            for (Activity activity : activities) {
                System.out.printf("%d | %s | %s | %s\n",
                    activity.getId(),
                    activity.getTitle(),
                    activity.getDay(),
                    activity.getTime());
            }
        }
    }

    public void printSortedList(List<Activity> activities) {
        System.out.println("Daftar Kegiatan (Terurut):");
        if (activities.isEmpty()) {
            System.out.println("- Data kegiatan belum tersedia!");
        } else {
            for (Activity activity : activities) {
                System.out.printf("%d | %s | %s | %s\n",
                    activity.getId(),
                    activity.getTitle(),
                    activity.getDay(),
                    activity.getTime());
            }
        }
        System.out.println();
    }

    public void printSearchResult(String keyword, List<Activity> activities) {
        System.out.printf("Hasil Pencarian: \"%s\"\n", keyword);
        if (activities.isEmpty()) {
            System.out.println("- Kegiatan tidak ditemukan!");
        } else {
            for (Activity activity : activities) {
                System.out.printf("%d | %s | %s | %s\n",
                    activity.getId(),
                    activity.getTitle(),
                    activity.getDay(),
                    activity.getTime());
            }
        }
        System.out.println();
    }

    public void printAddSuccess(Activity activity) {
        System.out.printf("Berhasil menambah kegiatan: %d | %s | %s | %s\n\n",
            activity.getId(),
            activity.getTitle(),
            activity.getDay(),
            activity.getTime());
    }
}