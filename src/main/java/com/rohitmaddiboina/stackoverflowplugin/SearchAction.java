package com.rohitmaddiboina.stackoverflowplugin;

import com.intellij.ide.BrowserUtil;
import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SearchAction extends AnAction {


    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PsiFile psiFile = e.getRequiredData(CommonDataKeys.PSI_FILE);
        if(Objects.isNull(psiFile)){
            return;
        }
        Language language = psiFile.getLanguage();
        String langType = language.getDisplayName();

        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        if(caretModel.getCurrentCaret().hasSelection()){
            String selectedText = caretModel.getCurrentCaret().getSelectedText();
            String query = selectedText.replace(" ","+") + "["+ langType +"]";
            BrowserUtil.browse("https://stackoverflow.com/search?q=" + query);
        }
    }

//    @Override
//    public void update(@NotNull AnActionEvent event){
//
//    }
}
