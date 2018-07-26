package br.com.patiolegal.reports;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.jboss.logging.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.patiolegal.dto.CompanyDTO;
import br.com.patiolegal.dto.CompanyDTO.CompanyDTOBuilder;
import br.com.patiolegal.dto.ProtocolRequestDTO;
import br.com.patiolegal.dto.SealDTO;
import br.com.patiolegal.dto.SealRequestDTO;
import br.com.patiolegal.exception.GenerateSealReportException;
import br.com.patiolegal.exception.PrintException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtils {

    private static final Logger LOG = Logger.getLogger(ReportUtils.class);
    private static final String PATH = "C:/Users/FERNANDO/workspace/patiolegal/patiolegal/src/main/resources/reports/";
    private static String image = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAALkAAAEQCAMAAADbFyX8AAAAilBMVEUAAAD////+/v4BAQFUVFTf39/7+/v4+PiKiorq6uoFBQWsrKy1tbXx8fEnJye4uLjU1NTCwsI1NTXl5eWlpaWdnZ1xcXHJycmQkJCBgYHZ2dkwMDCZmZnFxcXPz8/i4uJERERnZ2cjIyN5eXleXl48PDwXFxdHR0dPT08QEBAcHBxtbW1kZGQsLCwRZmJ2AAAgAElEQVR4nNVdiWLrqA4FnODYsZvN2dPsa9P+/+8NWsDYcZa26W1Hb15vm9j4WAghJCGEvEOK6N5ll3fJXu09lUrrz95Kd98lcb8l/VhLBdJK9oWhJtz+SYKnPfDAB5Cr/uSzD5eyvhQiiIU4Rp+/V6b93neRm77W8ixEbQhsfIjzytyjdEM4mj0sbooeUR+buxbQbd9CLsMa8q6bhI91PDx8OBUiRthBIMSu/pi44UX6tQa3CTE3An/zpjvSotIdAI8Njpd++ABw87T0DIgD4ngAt44eknUFYjLF14WbWncuv4Xc9Ff0JoK840c9ScpGXrADNYLpbRn2+eLY/YCRqkHyrnKe+mQ+wHfFjjI39m8LzC3koVzkvU5MHLQvQfuPT2cn954v804uMm9Zeltmomwq7KMEje7GDWw3kSs1FygnxDvThfD7shlWKlz4YDEO+A7z79hch4LD2E+NOjZafgj8TJMtPiUOhL0BJGbzJZ6HSnYIdCwGa+SClZtVK8VLNGleFuIoO1jRNj92bfp0eMIPqMd2vn61qMJ2/+j6iZ4xOhlWQStbfMLnkBs8TWA1PDKTunN0zSJ1G0k919Rpr9XoCttBcFXmOBqOSXQDvn07a9WjNNQ6TKPevNmg1/UG07KlZe+D3l/s0quT0nVp6VskLRkatk4G9hFWbcTd42ow3pxruz3zy77aIKLngZbTsr7l+2L36qe3j+nL3mINPNybCXZmeoDXNZx7MYr9YeQKOd6w8t223RWNSBZin0NFQmirOl6utZu72tsSvov7Yvz2kDjFq2qkUIWYyGqdVMlznDhjFJVTnXUTSlxyKHXtJY17PH7NJEa/wM/F5sYt1N50VM+HvvlnY192KCuttkrkKnxnUVkvYE7mxqDVSWN99dlGLI3uk3aub3drvfzGtHnIrw5K94lpY6JpyPNkYXp9hhfEaLY9htwMngOpQTGN8CYdKh7kOGE0Pvi5wJMcxscI1R6ZLjJELvdJaOjGXnNbyfNVc8EcI24rviezehJauQBfRg74ojWPzaPGO5RKs5AHCjNwOFp1vWd/bEed1PY1mh/mqdjIqSMLc9ei1TgfpzRdrXfbTf+V52XbuuyNE8mcf0XtZq7cqEubqYLnEwAOd5w1i0o0Fad+Kh10Evwwmry2klZnvkg1P1Xxy7c+yPoA1bYcWuhu/tIhEbeoFY9B86MNKmxEVyk5iWHQm/9W4T3kpoUJahTz30bySJvQtDDuUeOWgxXCRwiSXVEaDh22YFX1Xc4QCnEsGCa/h9jbpgP23P2HsGzFXPD81Y6bkeVRx2mtWiuUXgPKkn9/rx/nhhYOA5CZfu8ScInC4ZkGD9z+0pPUj+kSGzOfRCWBKSJXMrETZ5OBwVRq+isgm+s0eK2wdd3SMW3VhD8l8TiGZx+bkbw0W2wfRMkgpmsDnuuGNI8pvRVkDO1LKzNRbKVPxhVMnIp006isv0/vzTpfTlrDMn1xRXU4WjZe04rXXnTGy4qrM+pdJQfW7JsXFHuR5w0RW+2P6kmqlf2E+cdyUGsk7YXlvo7ardE7fRFfmabsUuO0HWed+aS+WCzq7WEy2jgzrTjHmj8GCN38f2Q/6fijpIB8QGIhThNedaZHUDNx8fmBE+PTejqdvjiLPA68S8vIQQarXysgiSohN00tQb5gJdy066R+hbSAufrO42nfwz+Vjj6uAfkXZESbbCYtW3bQj3MFIdw40aiRDFu6aFnqEPTjTbLD6aYd8w3kBmxC40m27ScDB91JSzpFaz4QNS3JVdPytdsV5OJHkQc4KeFM1VvzWKiFLOwC30nLxRqvjcWKBjRpw98mA30bkhsm7TIfpylNh4hcybpg5o15BuAB/csEiGBSQhOuxn6BdQ81tgCRlkOabISYSVbRW3FdT/xT5KgMUYAlmDRoCoEXCZBrmDhp4dtE9axBG/6Q8H6JZmgrwzqNLAF8F2HgZ/g1upU0KqFoemds/mva8HyNSFnnCJrfA3LlkUE3ubNg+9dkkO4iQKbB+qM5a2SkZYxfmm/rrDs7v430ksy03pZo9s7tRxtxRiVOIxZFJbunxn+DAivKsv5Bf67EmBZMhxRGphGVxm3/wm9RTO4+gx5kxABMRbrH35Yap9X0/OQnBsHzGNFBnsNvsVE3AiZ5gI5rNxU9SVQq0H77BZY46QNrjWyHoFu2pMs7OOdnn1Mr1r9/7ab929vbqfKb4FMGD84/ilgOoxK0oiInuRm9aITp6WeAozDknbSfHlfjUbP12l6g05M8B2gxhVGvPu8kWWNTO3i+puDBHja6bwtGAC4hYtFFuwUGJarzGfbG53Qic21/HGetSZS6xZbzD1S5BMN0MWyOVpZHj7A+Bv+iaSwl0/oVkMOw5NsjNLV2n5LI9fuoM/FWl4odVWgo43rQ9xV6/8AbLIb9zS6Hf+O5gXhHlo9wsXSARtA+z+hLnGPRSXG1AQqE0BO652xetSIu/aV9v0ZFH+hJYlfQwY1BNgEehDRmhtY+17pLHVJH/bK8zmGn4g6z18ga8h7M65EgVWQ3f0QdMmmn0XB2vP5YQ0e8JfF+R+SgGQNcKhHTq948cBypzdppLhleoC53vuXw5s2k9dqp+8Dt1dahg4w0yiKdj3bewwrPFkO8a4ljeuiQmw93dGmEMC7US0ABJ0O7xlB5T7fBXetqHRzY2blr8UXkzOAoW3QYNGZzGdZ7qRN+LTfm2QNuMU3O2EB5cdDFqb+NXU563a6JkM+BWREBVazjKMTTJL+rJyGq15xtatN3fhvPv8vepB0OizPdgkvyM3ouT87xDB/WPJ9T+LrZXzw8w/F5xhHYosYEdyPzGXihdXGY068DcDN7moJFvEXXsDo8cojEdCm5RhT1/I6Eegh/1cjeSwmqTupp2ganCq/bcYXQWZWQw9pTp4jrxAjs2p9tgD42MHYBXP5lM2RBRo/rYpiNZm0y3fg++h5kw+ivA4ZD8eV69OKx95Cu+cd8FtIAfxXg95rpso+34zEvNloPCH3yhv352h8xfeDHH1o5RzRj3686GLvV2LXN8YH7cq4czwMryBt43nAOP98tMARAQxoD63EICLqa5GuA0+hLSesoWSuo5rnMx59VxIxcm1bxJV9RAyy9u7JcaSi5YiZgrxMbUVPyqGkAjmQCnxzx+ox9eQv+Gh8Nc/aRwzjkH18WWY595SE/4afkLTrbixzPU7JeathG0+P5zm+zKbhJmnIBeYxxAdTuMMUFIlvA111EAAG2HTONFu9mThkjAJSWIT3lWBSVss9kJMmnK1xLPnIj3EFg8UT+fW2vTXzvt9c1qlh6lSAwfOMBj5bmDO+e4tPM9HKCUBd62SSFdFugKUlyJQcbayXkuhgARM95SmrZdU6OfEKLoz4qja3luICxlsdXUvwsRKmYMXIBMjngvjIvP4pcV4Uv5j2NHOK1YBLBN5gfQOad4h58L02vwxx1EKBlCDEJ4OzMgXF+RcXKuIt/JfmdYh960LvQVLsFP2tWfEB899g6ao39CSRobPtu2kKTCN5jjw8fT10nAMI1haQKTPfjvoYVGH86kFWoKpA36ToMaqb+O3fybpSYf0CSHFueZ0vbo682ziMGoRWupZn5jHENyOnb45ugYAoOA3EWgmfA/DE+8ADtW/IfkpVYRg4rOYhJUGSrZvWp+eQ9NzVMZ6B9g6lGPX7d0cCq2bagKKBZBywkxpiMegQxnCrpRs8JpLKNjlgQlUz4MoBP8S2nWKxRU8/wJZoVyFG4QYUtEXnma6VcXGg1tYfJMsD1H2AbN5Gr5poF+f3g7T7IrWrEJgIcGHmHb7rUswuaUwMxBe3e9w0K6Ao/uLeRdowAsxz5yFukXcB3SggttfKG0xMO0RkORUlyPqjDZxq5alj+Mn8D6KDCtwbCGRUOTPbAyhOLsLYuzpHRpD4roS9OXriJhZX66+i9oYcchBvMowS//vCQr7wb0Ffa34IUjVnOVxI6FZQkTgp7iYmCoHjXOEMGKKs07F/61C50bAgyNwczKinolra/PI1p0iQrsF+JHFmEQXb8euyNEuEZ3LxojXFEEPL1EXp3RMjNdzTTDmlIQtgrJsbB5L/rID/fACrqPrkUxQy/8jRE8yup6bp3XQF5E1vd46WdXNZ87WINFRhpC5reYwp2gAEQok2DMyegqdvcvwDTo8YwHMjdemQ9JboRiN9rAfmhgByTAFK02qd+AL2AvEcKBSfNwjQ6KFwDj37bNjEQxp0P8M3sG6KMkZO1yW8/jzBpwCA603iFh5zBhNuTVATelC4LGjn/ro3T5MYHW4zk0qSL6WPKTztY5+4IRc2xqUfz/UeNmUwaIBxxK/haoXzDoUJDZCYReYPcshhrDYpS4PtNAh7baA8GvqYoIyfhfvd+5/ud7YK6E8SHF3CgPV9o6hignQLKqcm9DOu0D429v+RJOpOQQIKT/5hYHltjyVIj1w1ohSp66RjXPdeQd6iDeCb37ndTBYwfdLzzmg608SnFYQZGNg6kNtoOG+qBJY0uY0Kg77VFbw76C1chbTD0RMH50fWQU8oHLLKhaws2QhE5CzeyYOEj9wxo7Exn3PXJ3kXLu80qYNhZbs+bRKlWM8vM4G6NZrNGSo3PqS+N4pnTnIi+Hj9/oyDmMSgHO/UXbYRSfktX5KpE+JT3J77R3j4Llzk92dmtxv2FsSXTG2nPYTQZpmq4OdfWdbJZ7Dv7N829p8a4AiUbIyhlR5eQjzEQPcM5eGsnhCAQuV5UMvbHVDKYJUOXH6bStNfuNLPReLOqHXdEh6PpgcasmQzrqUtq0nq825sRQqFC3x/Z95AHsOqjtYMojeNyfgsZwSuU4pHvAbB5yVaWEwZgb43ayWxgs0WvUzw9nkfJnHowXEzIH773vHewMvXkfCXJaDEfnYqWcInnC+TwDj0f/vLbrenQajvVxk4Dq96wv1l6bArQ4gwKJDhLJL/ssMkYv1wMsxyTBrPDQ55J1sRBeclXQh5Spjy6oApGlx1E5vMo4geFi6RxLDmjyK9WGbEgQL4nZ33OyNHnsgXR2eO3hkvGibAG3nXkYCwJkihVaMN6VBRn6IXD2fbNtf8NOtVGwxAfx5mlxa/xxVocnLuJnMYCTVY7X7HOGLmEPEubex0HbjfAl8jeW5u5qa5VuGCNn9GIW9xGnqCNRBPPxufmlro0bM+W9mXQFfk9jntOW3T/lQ1Fms7RdXZKbyOfMEygLG8hhmiYjJLVPeXxLdomqSyGNVG2Fc6qJW/SBfIQb8BlY8H0MYJOmwqupJF9k5ybu5Duy9MIzarnO8jlC15Wmv99gf+xCHUQXEbq6gAXg7fFVXYVcnLVe14l2+wP4S2hLw2bkLyuntv8OnLyWrZYvn6ZpgiJVi+Te8hpqUretNrv4ibZtvZAeA85ufRo3TS+0/CP0wx9P0tEXo5XXiCn4XDA37Pb7f48gWwr/QbID+WoxgVyDEizr706vvivKEAfp4IViVE65zLQy30WFBbEP+6lt/4sxWy1EIqL7X+X+yyWeGFk+f97FHDUiiyZ7AJ5MeCtwCtiF/sla/HfIz+iaoF9RbHvrJJgxitxEakfiXwp+qsK3XqGyIdaL8E0yOXoWPOJQiJNlPn33xyiGJ2VoBSB50WQhzbIeTnpDC3uBscUfhV5C+WicodhB5APipfTwnFgRez3oMccwGVQ/ldBFXJe8h4xqpT8JvI8wnmBwUN+kc0zxWXh8HezLvXlpEI7pXPkFZtS0MD53anorby+IQoKPDf6clH3aRLidsdJ/du06DF9/lbMGEkLGBaLhvCRB0F5ZX114/QnSclO1jSUZbOv1OaooL4oyXlZ0z+HlOvsbvv+1Q81OPs3yN1aNlPX9pB/ji6QX86u10jbfcUP1AdROt2jNtjaCKzLvPyqMH6D537W5L1rtca40anl5YeWfvksVSAvj9Crd+poMu/MJ1FYXl5V0OhwPB7Hfrkgoyrg7vSBmx9DnkvLPGk56oCjmDZx2/sWY5vN+HJ+BdnBb0N3U/4MraEFIK4/oLRMXS7lbuTqPtT55qTtkhhl5KFIen5WQAm5C0Koi1XzoJ3XOCh/u+zx7tOe+8hDHrrZjZGXvJ0JA3Kr3XGeRZr4F2aF/rnguR1DDWe7WDtnk0v0wBWnCGgzaJ0CLZGwG4G9fg1frKOXfZlJPlnjF036uMkPNPapLYoASjsPGzQKPG9a5Dazy84TDVGkwEFX7Oj1Ewk/SNflAWuPOeGLz3PMn7HWEW2QFQtFKVRMDSuVqmgFbquRyzvIgXCDqZHoF1RxBSOM+PYIckh28e/EjCmA7iO38Aqh/90FcvBnCOsPusrzwK6q1LzCkXt4HDlVbnDvbf44XUMeFnLnToXMfLo8uYo8oHgE9e8aeY7CJ9DQ9LZKpI8it9tmYvHuyjYtytLCRO25Ee5pbNrlF9xALmLhLUSIaZwOBOvDsGuvmjyIPI/QztiPJni7ZAVyvsBuwy7s9E/uIA/Ex4cdUAHxRrqs/EjnQREvV+k2cmdnt3OfQucK8oS4YlfI3ixBqvUmz83Ym6/tW5Cydz6MSObIW48idyraIJ/6N1cgn9FjM+7zmc/zR5DnEb5nIHcAH0C+oYVPa0297mXkPMZzrW08618j7xJyKBgIdPgschs0+vfI+QNt8z195J0C8iBPSPeRq+vIXUbETyC3nljnxMoXJQ8iv8HzH0VOe1iMaWG3bOeLh8eQ0xaGO8iTLyB37baqtKLiYJyocQY/5i0UcpruS4udxa4iD76EXNa6SB9V+lxJShWDlLYOPySva0X5mXeR39CK30KeLwh1Jc/ZEdtwABpVyFU1cnjPnt1H+HSeF6jMc9zjANAz2eOH+EGie8iNlG1zV+ST5fwmcgOdhbQjbdDEU+g+8lElz9EqtPuhon+IXHF6ayDMuvREvwVeipon5zSpXPDcJwy//yvkTrp7EG4LmK9ubLQ/g/xF/lPkdqGtOX8iKNi5ReRVcu7R5l8iZ0tRYJItapk49hMtHuY5rI2GT0buvCCqYjWn7Jw/9XKjvGjo49ISu90N/4rnvHxeSdqWB7T5kpy/WG/U83huL7nwWuB9vKA5jEajASOv5ffM7yB34Zf1zG0RqEAefw2587tVIefGAu8nbkBjWaqL4A7Pa+PxqJ/Uw9zDVTmHNr+C3OZoJlUW18SHzHRKH0bufGdK30LOY+cLVm5ABQX1JXKqmlRy7Cw+wfOmLFMlz7+E3Hp0m4UvGPlMVJDNYP7TyM9V0WOL5k8j7xaQ8+82Efr7yJtPQd6vQK5itlWypNlMXljiV1XIZ38LecQNn/Auu/p3mfs5cltI7M8gbyPDY/EG1VblymqZAnL25f5r5PN8V/4FcpffEaDZ4qVMRhXS8iBy5Z7nIRdXZiIsUHHFrzh3lkklz23J1aVL4kKaFJG3PsVzl+yaSeU2pxd9ufFiAtSGTI0S8ld7xzj3Ql96LbBUK/5F6Wcu/PVqkbfxxTqM/JqnqEjcc7DtPC+U0vORB/6nJeR2pROLrnPqzy94brcNx5i9n6cN5MVP28LFiR5EjnEOzIuJ7Q9hc+wLWzKBokvkvPM88INk4YXFpdzWMdr+PLcsGVcjFw8h7xFXodieqxFLru0HkOOGfXzlOLZl81cXcSK7fBYBv4cTrO3XeY5JgLGNk2IZHcit1o8hx51VcRz4IUkedcUROrffzhy3BKsaxb0QXEVOu5Yukat0LwIvwgrPVlZa6Ca74YmR818UDzW6OS7YgByKwABnYJGTpeg4h12A2xhsKe/hNeROgZYTYeHOaFng7G7CUeOeKBHUqg9d2baQjGW9KVxjN5J70XO/z2lnXh6C7yldgVw45GqeMFWkjUCX53mZ7y3n7AubSYGaIZRbbdk/Q3vhfGPf5jR2eQh1e3MbckeGtglUWlrZRpoR85x8ua+XyP1w7wVy/Dqtt+fzNpVxV+WbmArlw/h7LrQ/MXdPojy1x7tXKX9zOaX/yFIrrCeHud1iZ9ecKniuvRWSl12jbpI7oYBKgfq38G95A3y8grvS/4p5TnGiYc7zJ2W2/TT9n5GTEp2DtDT/T8glx/0nwHMs0fNTWX/PJ9oC0P7fIbeuxjLPn5PR+pOkeKpaEHISHEO94p6Av0XHs8yT4Bxytn68fZV/kKisGqX6RTnP238decyl7GjXSpojx73lvUuH3h+iASKndaRDHtM6L/rTyGmtQZlNaS7nmDyQ/mHkXKqAKuLEaa4VsfBn+NvwbhBXiggxvewjpNmfXNlAP7op/nvEDhKqtXTAutBUPyRDa/vl3v2/SW0WaIEVMwWV8gywfo+Wu3t3/yZhwiU5AzaIfI7DkuJ2d04Y+l3CZRvt0+4j8gjX1lupyjnIf4z2OBJtWi6exCG4hpL6I2eeXCFyoc9wVA4l1j9H5B9cnPDvEhViGyDyHiF/gTn0hK6F391tfpvIsXSkzf9Ucx6TSOA1yhV+/hZRXjHq7T0jp2GJJ+D+7m7z21Rny0pQGoDAuADwuYXFGj/+Ls9RKbaFrRwI4RHK4SExKtZj/ku0k3RSFWXVUaUCiiZQrd5f3bN9k6hmDhUCHDJykh3aQfIXTt+qIGdYkUzUWVpsKQslL8ph/RWKA9iToXDuiWmrMyJf4rfo9v2jFnqMMSW0VGz8ApGTR54KQvxNa9FaJ3gmlLEONSOnICsFUn+9Tk4l2aIPY0EVMXmEss2LdSpV604bv0WvMg+D1x1yqPBp5AcDURchnyvE+7u+teDGNh5sAU92oCl+HVrkVASK9gRo+Xa7hfKzvwY9cGd7PtjAEgViiNqcQqOUYkC7/zPPb3ef0uGoXFzxk7Qct9LZ1bNSi0TBc6ot03fINZavpELg2ixL73PB1mlM29nZLroDkR/t4k/EnkTk1dmmmybWhFThjVPUfZooq/gCzkYjaSH5OWEl3fSBttxhCmi+zZvjWunIKvvywYU47c6j1iQ/mP3B47OmGPBitycF8BA5CnpsU+veH2gJ9+kWdv2H7U7WeD92q4fJW/fwPu633HlGXuyv+piuEo3YNIG35KJ/jJxWd6QzW49woe4dz5EHRmHfdhr12sNOhwOwnc5wskjzarMuiOiY3rx+LDBTAHs5NTpDQdySHLkzVyhD/hEDwDuAwtVckHdIuZOu/OAIbFq6M7DwxDBll/o25mzTlywngTY320Hyyn7rHEfOS+WqsUrvX74w7E1crFnBsU73eA4pAgqrFaKTosBzLrRA+Yzz2xMM3h3mXOssPz66x9Wsk8qL2LWyZ7lH807SB3VWx6GQnzSgwDt762nwgw4YekfHkE10sMgpD+CFGLS/h9zbPOAXWh+bP5PdobZdSTlZDQbv5oNjrbbcRXxMjrT6wS/VbvTwjXkBkNDUww+ym6IZOez2DrhFOM7y1lGzuGc8h96kQ4RRWQ85or3HDdtCdCm56S1kow44dyonz5TL418S1eolX1BNFqVF44EzMSk7OjfnOhdgeZJLMB5LHr/g7QkXoe+S/0nsQxzuS9x+aNoEPb6E6/2yCeArvP44czHv91/i3+6dXYLhHFl3Cjm/8ea8HvqPxTLzY42v1CLvGe65ws/TiENTeF4KagBc6dZ85N6JJVXQ+cwIrvaUlpArqGUPJz1Q9YvbBqNfoBkOpTBv3AxRQcx5iVuTXPl7MaGq/nTSBQpjhilTBb3IGa2VZAYf5aYMeBrSReTodQGdgyrdWvDVLWGqkyMKLQ3b/A1KnVhJPmJi3mapDt9oZGONL1GqCkopXteQNzHLBcYNbBBSZeRu6KIFjwURKpHTWPCeSXNYRCMwZGGAXENMgmuhuxtG2M5ao9Sfpa059euKEY/X0GwmrvPJQ+QYSKUfKeepcYXnWP/K35ZMayhjZPYmr4lpdYViL7nqWoa5Sz0Ue85BxIMKbOX3wsOrCSd7KvUY4Ib4S+R44lmMW6yuqxcM0fg8R0U9tflOnDCOS1pIEWwk8CKgUgZcDJQdsKNSdkR1DDnAo+rgSlKJJ2+njfBA1HCM1mjia17rv2EFs7buT8oExTEMIdczPPEF2oM8SNO2hs1lXJDfawZP9qlAHtPON0UdVdANfsL4K53pMSSbY1kNfFd8JM0iY2nzw4w8x+yjhIXWcST42CtUKV1NB6aUTlSjkVXBczjECU2cGUmAn/jk8RxX1vbwNxKeUlt0nG7hiRqXRJmMQub5B3nSJDn6dqAksbhDi3YgKJvenBYZwOr0Ym8IHmKIOhWPFfJuKpQveKWrMRyt8UCnEnIsp1qATj7J4USsl9sBzBJrN7nDtPnybu24uVWGinwkFzlMl677gAcyZePcOv2Ej12hIJjW1PElnmdFQ9zCIA0DPZtrDoBymgo+QaPHgRHFFWGLfUcTygW9U/cvSHJG8uopP5QhjVYFWKYLUSq8GMA+2SLPKbCkUMO80aFgAcFSfMxxkMeOeXjjfo2ZXZI4Kte4jWlhTHvRWUVdQ04H4WCKM2rN5KJkZOlkBt4RtafNqOA7CB1ATUf6BTyu6CS2Fk2YOPRkWb1kxUEa4xHEpu8TtBfyrP9L5IpkEM930lZVFflwkdWI3pkj+VEbzNqY6gS7tGZqHDmduey37rw5SgsP12Fp9U1ZIbbA0TQs9nZRWihmFMD7aezLQxF6+Sw+SblJHxQvyKTieqUTbCsh5fRGN23p5RSdsYY39PxHq8IcQhMLagpYCmEh11vIJZX5CHizslFHHz5wKrtfID86A5Zgz1mzypYE4WkKpywsljCx2m9YbEsVDgDvhqQLmiQ5g/IavYxctUk41yktjXlvBdHqAjiZLbQJIU7dkSmIHPJ84FbWwg2SK+WFLvvl1rxA8ilC3qmFU+x3kOPiCDp5xWO/7hkU60ETV4Eu+9pcPhxY9xycwsv2P58E/ML48Hqrfsz/1t3VuN9pR4UakWm7ueFtigGdTY356DvcnQF69h7PVchqPGMnRClytN4k5JsZ8ewAAAblSURBVFxzj9XppNNqtsB3pK3RS41RzkmHkM9HWeu1jTfYscZs1NG8ubHbiPjnXFJxRPCgxGCqhSWglzy3G2Fi0VE0SvNDmuxenLfaqNOzz7YaHg41Nv9P06jXY0s0WvSiKCJ51RYs3cTY9aQzendHn+fF4eb8Vhm5U99SdVGF9ILnktKj4tiew8AWjGvUSWJtnLQ9v4lLy+c/PLnM/XHuo7A+bzbeXzymWKkMWD/AHfYE1PblAKuoOS/xxGjotn2kqZBmby2uOXjj5WCWzHtRsTNz/1vxiSqNFvNktqmtKxvjzg4m3DV0cFh8Of9VI4foP9fD6pIhr2W6vLBBQcd6n30st+dx1my9zieLNPR2WRisk/lrq5k1Bu+Hoqe3ctUViGkPK4CaW9/I876t4Hglzw34KCaB6YYa68RKfS7HRfJKgJ+mwPtZ/iYWx5C3kqRv9HbTi8F5HbmGfkLB3oVWhWR+yOEWrAtEn3s7LiZqhvqUNU1UyfJq5LQwRpbuUlbecv5QFmYg8u1p6LP7ZM8kVlWmO3YRzq84uK8gZx6be18iqxjS7Rel4yFCbdZdSNJEMl0LMltbV4BfQQ6jsmnLckyo7Kyij34OurHHrNKX9ZiXki2pq4FfRY4nobMOf7Xzh4x+MhPzJZeLoeVaJvUVll9Hzssr1Hx9qTm+QzuoHhqqDxPPnWMKEoBpQKGjGIFXD8+byCWu0ontK+18S/rpKQ3InENd2iiS2tjxlFwVlVvIkcO9F6yEahS73QkKhiyKzPPGqmlpb1WKGUz1JR8qDaaavl40/QbPAWW4RZYEuA5zDHj94Ij/9+EjRljWKS6Mjm448BLauk5fQS4pUgqtmP+2kWeLtF7E085vG0TWvNGoeWm9/NG7E6i8g1xrTOMh3rSckWr403xSkv17XbLNq7QdWBA7CKX6DnJkcL0LKgr4W1tYcQQGNcHg+6rE8BS7qrtxaUQTBz9OSeOboB9DDsXZVw7eTPOYwTdowZrgSxqScJ/rbmVo2N6KXSmDOyL+MPIwr5Aj1h23joNlSud4E+B15Ob/m7pU+UppUhNWp7ynd0T8QeRIsDjhygi1eiHU3H4Xt0PWZcwx6ZMGLwcJNwpKTDPnxblV30KO835MSkaMUzuo8MGLT81NgdWDfjKAl7C/q9+afr6A3Iz7F9fPwvnVYDe6sSJnn0r+esnCgg+i85bb/iN13VL5GnJDYQN1OEnGKATWOM6HSZe+vIU4xpDINFFW4eHP4UE426X24NkUn0NumLs4Mjw81zXMZ1X4p3MQt20CfK1pp7iqHh7zL/edR0bmF5ADo1pviI4G68zPbTJC067dwS6WGA7x5GTnrRFn+kEJ/yxyojB7I+8LVf+IWM8oji3xOK5Yi5pPP1q5Y8/coxNKhSMf/bl379HfRA7Ybc/DAzclyZwvKwUFfmSFXC6d+T4XNyf9HHIUCyoWQDFIsZ3nAoCT08clcnPdJszHpVlmjoQ3C2x60q8p+DPIidL+yQI3dOxIL4ULMznjPEoDp43Yc5UU1vSQPVurCa8af628wxeQo9cr7DspMNRtat/lWS+4xHwDCozP1M1ccA3b5v8EOePTMyczgKDPB/co7HdvWjVDMF8aaxnxPI906od4+T9EjpSO0NKw7DWKJpfVBJUMufgoNofo05F7HQgvVfvdfhw5DLQMa8IHdlUW5rPqnGzwWNRs5Nj8k7CrEJj+kuhHJ/rnImdHs04oKhWTJZXlSoaqw4paPr/UD3nwYNcqRAD+JfKcEqrGRoiMEiErjHMUdogNM7H6gqpamWt35dj5F+j7yGEyPLFyxnnVVRQyYzEmjQeCdRTWbz3tOJvrd5EbBoc88FCN7yJeL+iT6CgOEs1dxGYPiRj6O3LyLORE0buV4QCWkbBcVbKJ4Vf4neqcAfJR+Sztr9KzkJtZf51n8rTQmadSzKsxnUInyMR4ZN735YToWcid04HmGfQIKnKuaRR4/K/FZwQ+g57Hc1jw5TsPmtYzozgfw+CupXfb+AQ9ETkokJqzZTjyjKJCOnOk5XX/5ufpmchBj7uC2C762mQnfiLVZSD5G/RE5EQdZ+IucKX0ymOz/VSGy+cjV7J+YiZ/hJQQhDT5vgIv0dN5buzYNZ/NAxmWSwLepoMln0lPR24o7XKAqoE5rbEbrk+lH0BOKTIoMCPSh9nXVj236enIKcNhzUcioY5cyacLufwZaYEUOmc9xlwO/On0I8gl7edgn8z8+fwG+iHkmpMSMYH5RmjwG/RTPOedPQEWYP8R+jnkussG7w/RzyFXmLq4fPak7+gHkaP1NX+qleXTDyKHAyVXn3KJf4p+ErlMRP25lq1PP4ccaHz/ki/Tf1SF+TfyFh+SAAAAAElFTkSuQmCC";
    private static String qrcode = "data:image/png;base64,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";

    public void generateProtocolReport() {
        String sourceFileName = PATH + "protocol.jasper";
        List<ProtocolRequestDTO> list = new ArrayList<>();
        ProtocolRequestDTO protocol = new ProtocolRequestDTO();
        list.add(protocol);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

        Map<String, Object> parameters = new HashMap<>();
        CompanyDTO company = new CompanyDTOBuilder().withName("Deposito Legal de Veículos Maringá")
                .withPublicPlace("Avenida Paranavaí 1489 (Rotatória Yamaguchi - Rodovia PR 317)")
                .withPostalCode("87070-130").withCity("Maringá").withState("Paraná").withPhone("(44) 3030-3040")
                .withImage(image).build();
        parameters.put("TITLE.LINE1", company.getLine1());
        parameters.put("TITLE.LINE2", company.getLine2());
        parameters.put("TITLE.LINE3", company.getLine3());
        String imageWithBasePrefix = StringUtils.replace(company.getImage(), "data:image/png;base64,", "");
        InputStream imageIs = new ByteArrayInputStream(Base64.decodeBase64(imageWithBasePrefix.getBytes()));
        parameters.put("TITLE.IMAGE", imageIs);

        try {
            String printFileName = JasperFillManager.fillReportToFile(sourceFileName, parameters, beanColDataSource);
            if (printFileName != null) {
                JasperExportManager.exportReportToPdfFile(printFileName,
                        "D:\\protocolo-" + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".pdf");
                // JasperPrintManager.printReport(printFileName, true);
            }
        } catch (JRException e) {
            LOG.error("Erro ao gerar PDF", e);
        }
    }

    public InputStream generateSealReport(SealRequestDTO request) {

    	LOG.info("Dados recebidos na requisicao para geracao de lacres: " + request.toString());
    	
        String sourceFileName = PATH + "seal.jasper";
        List<SealDTO> list = new ArrayList<>();
        String imageWithBasePrefix = StringUtils.replace(qrcode, "data:image/png;base64,", "");
        InputStream imageIs = new ByteArrayInputStream(Base64.decodeBase64(imageWithBasePrefix.getBytes()));

        SealDTO protocol = new SealDTO();
        protocol.setProtocol(request.getProtocol());
        protocol.setAuthentication("843fa876-dbe9-4dc8-8532-8ad9d1bd8f41");

        try {
            protocol.setImage(ImageIO.read(imageIs));
        } catch (IOException e) {
            LOG.error("Erro ao recuperar imagem:" + e);
        }

        while (list.size() < request.getAmount()) {
            list.add(protocol);
        }

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

        Map<String, Object> parameters = new HashMap<>();
        try {
        	LOG.debug("Preparando para gerar lacres...");
            JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);
            InputStream inputStream = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint));
            LOG.debug("Lacres gerados.");
            return inputStream;

        } catch (Exception e) {
            LOG.error("Erro ao gerar lacres: ", e);
            throw new GenerateSealReportException();
        }
    }
    
    public ResponseEntity<InputStreamResource> printToPdf(String fileName, InputStream inputStream){
    	ClassPathResource pdfFile = new ClassPathResource(fileName);
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		
		try{
			return ResponseEntity
					.ok()
					.headers(headers)
					.contentLength(pdfFile.contentLength())
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(new InputStreamResource(inputStream));
		}catch (Exception e) {
			LOG.error("Erro ao gerar PDF com lacres: " + e);
			throw new PrintException();
		}
    }
}
