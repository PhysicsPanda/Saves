package learn;

public class Merge_Sort {

	// l, m, h : low index, middle index, high index
	public static void Merge(int[] n, int l, int m, int h) {
		int[] res = new int[h-l+1];
		int res_len = h-l+1;
		int as = l;
		int bs = m+1;
		
		for(int res_idx=0;res_idx<res_len;res_idx++) {
			if(as<=m && (bs>h || n[as]<n[bs])) {
				res[res_idx] = n[as];
				as++;
			}else {
				res[res_idx] = n[bs];
				bs++;
			}
		}
		
		for(int i=0;i<res_len;i++) {
			n[l+i] = res[i];
		}
		
		return;
	}
	
	public static void MergeSort(int[] n, int l, int h) {
		if(l<h) {
			int m = l + (h-l)/2;
			MergeSort(n, l, m);
			MergeSort(n, m+1, h);
			
			Merge(n, l, m, h);
		}
		
		return;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = {5, 8, 9, 3, 7, 10};
		int len = arr.length;
		
		for(int i : arr) {
			System.out.print(i + " ");
		}
		
		MergeSort(arr, 0, len-1);
	
		System.out.println();
		for(int i : arr) {
			System.out.print(i + " ");
		}
		
	}

}
