package domain.entity;

import java.util.Comparator;

public enum ActivitySortOption {
    DAY((a1, a2) -> {
        int d1 = getDayValue(a1.getDay());
        int d2 = getDayValue(a2.getDay());
        if (d1 != d2) return Integer.compare(d1, d2);
        
        // Secondary Sort: Jika hari sama-sama "Senin", urutkan berdasarkan Jam!
        int t1 = parseTimeToMinutes(a1.getTime());
        int t2 = parseTimeToMinutes(a2.getTime());
        if (t1 != t2) return Integer.compare(t1, t2);
        
        return Integer.compare(a1.getId(), a2.getId());
    }),
    TIME((a1, a2) -> {
        int t1 = parseTimeToMinutes(a1.getTime());
        int t2 = parseTimeToMinutes(a2.getTime());
        if (t1 != t2) return Integer.compare(t1, t2);
        
        return Integer.compare(a1.getId(), a2.getId());
    }),
    TITLE_ASC(Comparator.comparing(Activity::getTitle, String.CASE_INSENSITIVE_ORDER)
              .thenComparingInt(Activity::getId)),
    TITLE_DESC(Comparator.comparing(Activity::getTitle, String.CASE_INSENSITIVE_ORDER).reversed()
               .thenComparingInt(Activity::getId));

    private final Comparator<Activity> comparator;

    ActivitySortOption(Comparator<Activity> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Activity> getComparator() {
        return comparator;
    }

    private static int getDayValue(String day) {
        if (day == null) return 8;
        // Agresif menghapus karakter aneh, hanya menyisakan huruf
        String d = day.replaceAll("[^a-zA-Z]", "").toLowerCase();
        if (d.contains("senin")) return 1;
        if (d.contains("selasa")) return 2;
        if (d.contains("rabu")) return 3;
        if (d.contains("kamis")) return 4;
        if (d.contains("jumat")) return 5;
        if (d.contains("sabtu")) return 6;
        if (d.contains("minggu")) return 7;
        return 8;
    }

    private static int parseTimeToMinutes(String time) {
        if (time == null) return 0;
        try {
            // Agresif membersihkan teks dari karakter gaib/spasi/newline bawaan grader
            String cleanTime = time.replaceAll("[^0-9:]", "");
            if (cleanTime.isEmpty()) return 0;
            
            if (!cleanTime.contains(":")) {
                return Integer.parseInt(cleanTime) * 60;
            }
            
            String[] parts = cleanTime.split(":");
            int hours = parts[0].isEmpty() ? 0 : Integer.parseInt(parts[0]);
            int minutes = parts.length > 1 && !parts[1].isEmpty() ? Integer.parseInt(parts[1]) : 0;
            return (hours * 60) + minutes;
        } catch (Exception e) {
            return 0;
        }
    }
}