import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForwardChaining {

    public static void main(String[] args) {
        // Fakta awal
        Map<String, Boolean> fakta = new HashMap<>();
        fakta.put("A", true);
        fakta.put("E", true);

        // Rules
        List<String[]> rules = new ArrayList<>();
        rules.add(new String[]{"A", "B", "C"});
        rules.add(new String[]{"C", "D"});
        rules.add(new String[]{"A", "E", "F"});
        rules.add(new String[]{"A", "G"});
        rules.add(new String[]{"F", "G", "D"});
        rules.add(new String[]{"G", "E", "H"});
        rules.add(new String[]{"C", "H", "I"});
        rules.add(new String[]{"I", "A", "J"});
        rules.add(new String[]{"G", "J"});
        rules.add(new String[]{"J", "K"});

        // Fakta baru
        Map<String, Boolean> faktaBaru = new HashMap<>(fakta);

        // Proses forward chaining
        boolean iterasi;
        do {
            iterasi = false;
            for (String[] rule : rules) {
                String kesimpulan = rule[rule.length - 1];
                String[] premis = new String[rule.length - 1];
                System.arraycopy(rule, 0, premis, 0, rule.length - 1);
                boolean ruleTerpenuhi = true;
                for (String P : premis) {
                    if (!faktaBaru.containsKey(P) || !faktaBaru.get(P)) {
                        ruleTerpenuhi = false;
                        break;
                    }
                }
                if (ruleTerpenuhi && !faktaBaru.containsKey(kesimpulan)) {
                    faktaBaru.put(kesimpulan, true);
                    iterasi = true;
                }
            }
        } while (iterasi);

        // Menampilkan hasil
        System.out.println("Fakta awal: " + String.join(", ", fakta.keySet()));
        System.out.println("Fakta baru: " + String.join(", ", faktaBaru.keySet()));

        // Pengecekan apakah K bernilai benar
        if (faktaBaru.containsKey("K") && faktaBaru.get("K")) {
            System.out.println("K bernilai benar.");
        } else {
            System.out.println("K bernilai salah.");
        }
    }
}
