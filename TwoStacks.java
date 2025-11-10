import java.util.Stack; // Gerekli değil ama standart kullanım için bırakılabilir.

public class TwoStacks {
    // Member variables
    private Integer[] twoStacks;
    private int capacity;
    public int top1; // Stack 1'in üst indisi (soldan sağa büyür, ilk eleman top1=0)
    public int top2; // Stack 2'nin üst indisi (sağdan sola küçülür, ilk eleman top2=capacity-1)

    public TwoStacks(int capacity) {
        this.twoStacks = new Integer[capacity];
        this.capacity = capacity;
        this.top1 = -1;       // Stack 1 boşken -1
        this.top2 = capacity; // Stack 2 boşken capacity
    }
    
    // Getter for capacity (Diğer metotlar için gerekli)
    public int getCapacity() {
        return capacity;
    }
    
    // --- Diğer Getters & Setters ---
    // (Orijinal kodda zaten verilmiş, buraya tekrar eklenmemiştir.)
    public Integer[] getTwoStacks() { return twoStacks; }
    public void setTwoStacks(Integer[] twoStacks) { 
        // Karmaşık setter mantığı buraya dahil edilmemiştir.
        // Genellikle TwoStacks yapısında bu kadar detaylı setter gerekmez.
    } 
    // ...

    // Member methods
    
    /* isEmpty() methods */
    
    /**
     * Stack 1'in boş olup olmadığını kontrol eder.
     * Stack 1 boşsa top1 = -1 olmalıdır.
     */
    public boolean isStackOneEmpty() {

        return top1 == -1;
    }

    /**
     * Stack 2'nin boş olup olmadığını kontrol eder.
     * Stack 2 boşsa top2 = capacity olmalıdır.
     */
    public boolean isStackTwoEmpty() {

        return top2 == capacity;
    }

    /* isFull() method */
    
    /**
     * İki yığının da dolu olup olmadığını kontrol eder.
     * İki yığın da birbirine yaklaştığında ve aralarında boş yer kalmadığında dolu kabul edilir.
     */
    public boolean isTwoStacksFull() {
        // top1 ve top2 karşılaştığında (top1 + 1 == top2) dolu demektir.
        // top1 en son eklenen elemanın indisine işaret eder.
        // top2 ise Stack 2'nin en son eklenen elemanının bir sonraki boş indisine işaret eder.
        return top1 + 1 == top2;
    }

    /* push() methods */
    
    /**
     * Stack 1'e yeni bir değer ekler.
     */
    public void pushToStackOne(int value) {
        if (isTwoStacksFull()) {
            System.out.println("Stack Overflow: Two stacks are full.");
            return;
        }
        
        // Önce top1'i bir artır.
        top1++;
        // Ardından yeni indise değeri ekle.
        twoStacks[top1] = value;
    }

    /**
     * Stack 2'ye yeni bir değer ekler.
     */
    public void pushToStackTwo(int value) {
        if (isTwoStacksFull()) {
            System.out.println("Stack Overflow: Two stacks are full.");
            return;
        }
        
        // Önce top2'yi bir azalt.
        top2--;
        // Ardından yeni indise değeri ekle.
        twoStacks[top2] = value;
    }

    /* pop() methods */
    
    /**
     * Stack 1'den en üstteki elemanı çıkarır (ve döndürmez).
     */
    public void popFromStackOne() {
        if (isStackOneEmpty()) {
            System.out.println("Stack Underflow: Stack 1 is empty.");
            return;
        }
        
        // Değeri null yap (isteğe bağlı, ama temizlik için iyidir).
        twoStacks[top1] = null;
        // top1'i bir azalt.
        top1--;
    }

    /**
     * Stack 2'den en üstteki elemanı çıkarır (ve döndürmez).
     */
    public void popFromStackTwo() {
        if (isStackTwoEmpty()) {
            System.out.println("Stack Underflow: Stack 2 is empty.");
            return;
        }
        
        // Değeri null yap.
        twoStacks[top2] = null;
        // top2'yi bir artır.
        top2++;
    }

    /* top() methods */
    
    /**
     * Stack 1'in en üstündeki elemanı döndürür (silmez).
     */
    public int topStackOne() {
        if (isStackOneEmpty()) {
            // Hata durumunda StackException fırlatılabilir veya MIN_VALUE döndürülebilir.
            throw new RuntimeException("Stack 1 is empty.");
        }
        return twoStacks[top1];
    }

    /**
     * Stack 2'nin en üstündeki elemanı döndürür (silmez).
     */
    public int topStackTwo() {
        if (isStackTwoEmpty()) {
            // Hata durumunda StackException fırlatılabilir.
            throw new RuntimeException("Stack 2 is empty.");
        }
        return twoStacks[top2];
    }

    /* Other methods */
    
    /**
     * Stack 1 ve Stack 2'nin en üstteki elemanlarının yerini değiştirir.
     * @return Takas başarılıysa true, yığınlardan biri boşsa false.
     */
    public boolean swapTops() {
        if (isStackOneEmpty() || isStackTwoEmpty()) {
            return false; // Takas için iki eleman da olmalı
        }
        
        // Geçici bir değişkende Stack 1 üst elemanını tut.
        int temp = twoStacks[top1];
        
        // Takas
        twoStacks[top1] = twoStacks[top2];
        twoStacks[top2] = temp;
        
        return true;
    }

    /**
     * Stack 1'deki tüm elemanları temizler.
     */
    public void makeStackOneEmpty() {
        if (isStackOneEmpty()) {
            return;
        }
        
        // Sadece top1'i başlangıç değerine (-1) getirerek mantıksal olarak boşaltabiliriz.
        // Ancak bellek temizliği için dizi elemanlarını null yapmak daha iyidir.
        for (int i = 0; i <= top1; i++) {
            twoStacks[i] = null;
        }
        
        // top1'i sıfırla.
        top1 = -1;
    }

    /**
     * İki yığının da boş olup olmadığını kontrol eder.
     * @return Her ikisi de boşsa true, aksi halde false.
     */
    public boolean isBothStacksEmpty() {
        return isStackOneEmpty() && isStackTwoEmpty();
    }

    /**
     * Stack 1'den belirtilen sayıda elemanı (elementCount) pop edip 
     * Stack 2'ye push eder.
     * @param elementCount Taşınacak eleman sayısı.
     * @return İşlem başarılıysa true, Stack 1'de yeterli eleman yoksa false.
     */
    public boolean pushToStackTwoFromStackOne(int elementCount) {
        if (elementCount < 0) return false;
        
        // Stack 1'deki mevcut eleman sayısı
        int currentSize1 = top1 + 1;
        
        if (elementCount > currentSize1) {
            System.out.println("Insufficient elements in Stack 1 to transfer.");
            return false;
        }
        
        // Gerekli boş alan kontrolü
        // Kullanılabilir boş alan: top2 - (top1 + 1)
        int availableSpace = top2 - (top1 + 1);
        if (elementCount > availableSpace) {
            System.out.println("Insufficient space in the array to transfer " + elementCount + " elements.");
            return false;
        }
        
        // Elemanları taşı
        for (int i = 0; i < elementCount; i++) {
            // Stack 1'den en üstteki elemanı al
            int value = topStackOne(); 
            
            // Stack 1'den pop et (top1'i azaltır ve elemanı null yapar)
            popFromStackOne(); 
            
            // Stack 2'ye push et (top2'yi azaltır ve elemanı ekler)
            pushToStackTwo(value); 
        }
        
        return true;
    }
}
