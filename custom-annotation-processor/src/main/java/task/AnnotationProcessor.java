package task;



import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.QualifiedNameable;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes(
        "task.CustomToString")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements
                    = roundEnv.getElementsAnnotatedWith(CustomToString.class);

            for (Element element : annotatedElements) {
                if (element instanceof TypeElement) {
                }
                try {
                    writeToStringFile(element, annotation);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(Arrays.toString(element.getKind().getClass().getDeclaredFields()));
            }

        }
        return true;
    }

    private void writeToStringFile(Element element, TypeElement annotation) throws IOException {
        String packageName = ((QualifiedNameable) element.getEnclosingElement()).getQualifiedName().toString();
        String fullClassName = ((QualifiedNameable) element).getQualifiedName().toString();
        String classDeclare = "public class " + element.getSimpleName()
                + annotation.getSimpleName() + " extends " + element.getSimpleName() + "{";

        JavaFileObject toStringFile = processingEnv.getFiler()
                .createSourceFile(fullClassName + annotation.getSimpleName(), element);
        try (PrintWriter out = new PrintWriter(toStringFile.openWriter())) {

            if (packageName != null) {
                out.print("package ");
                out.print(packageName);
                out.print(";");
                out.println();
            }

            out.println(classDeclare);
            out.println("    @Override");
            out.println("    public String toString() {");
            out.print("         return ");
            out.print("\"This is my implementation of toString method.\";");
            out.println("    }");
            out.println("}");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

