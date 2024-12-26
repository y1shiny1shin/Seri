package Seri;

import org.apache.commons.fileupload.disk.DiskFileItem;

import java.util.Objects;

/**
 * Gadget chain
 * DiskFileItem.readObject()
 *     DiskFileItem.getOutputStream()
 *             DeferredFileOutputStream.write()
 */
public class FileUpload1 {
    public static void main(String[] args) {
        char[] x = new String("HFCTF2022").toCharArray();
        System.out.println(x[1]+111);
        System.out.println("HFCTF2022".hashCode());
        System.out.println(Objects.hashCode("HFCTF200p"));

    }
}
