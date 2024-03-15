using DocumentFormat.OpenXml.Wordprocessing;
using EditorTeleric;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using Telerik.Windows.Controls.SyntaxEditor.Taggers;
using Telerik.Windows.Controls.SyntaxEditor.Tagging.Taggers;
using Telerik.Windows.Controls.SyntaxEditor.UI;
using Telerik.Windows.SyntaxEditor.Core.Tagging;
using Telerik.Windows.SyntaxEditor.Core.Text;
using UnifyCloud.CloudPilotTool.SettingsReader;
using UnifyCloudMigration;
using static EditorTeleric.SimpleToolTipTagger;
using static System.Net.WebRequestMethods;
using File = System.IO.File;

namespace CloudPilotTool
{
    /// <summary>
    /// Interaction logic for AutoCodeEditor.xaml
    /// </summary>
    public partial class AutoCodeEditor : Window
    {
        
        public static string[] str2 = null;
        int countfile;
        int filecountno = 1;
        private BracketFoldingTagger foldingTagger;
        public AutoCodeEditor()
        {
            InitializeComponent();
            this.foldingTagger = new BracketFoldingTagger(this.std);
            this.std.TaggersRegistry.RegisterTagger(this.foldingTagger);
        }

        //public void Clear2()
        //{
        //    std.SelectAll();

        //    std.Selection.Text = "";
        //}
        private void Window_Load(object sender, RoutedEventArgs e)
        {

            // string filepath = @"C:\xaml\Utility.cs";
            //+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+
            App.mainfiles.Clear();
            countfile = 0;
            //App.lstAppData.Clear();
            lblMigAppName.Text = ServiceDetails.ActionServiceName;
            if (ServiceDetails.ActionServiceType == "Application")
            {
                //lblMigAppName.Text = ServiceDetails.ActionServiceName;
                var textapppath = App.lstAppData.Where(x => x.ApplicationName == ServiceDetails.ActionServiceName).FirstOrDefault().SourceCodeLocation;
                var xd = Directory.GetFiles(textapppath, "*", SearchOption.AllDirectories).ToList();
                foreach (var ab in xd)
                {
                    //if (ab.EndsWith(".csproj") || ab.EndsWith(".vbproj") || ab.EndsWith(".config"))
                    if (ab.EndsWith("Web.config"))
                    {
                        var shiv = ab;
                        //fileList.Add(ab);
                        App.mainfiles.Add(ab);
                        cmbFilesShow.ItemsSource = App.mainfiles;
                        cmbFilesShow.SelectedIndex = 0;
                         Filecount();
                        Displaycode(1);

                    }
                }
                //var fileexc = xd.Where(x => x.EndsWith(".csproj"));


            }
        }




        //+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+
        string filePathSave;
        public void Displaycode(int x)
        {
            int temp = 0;
            foreach (var file in App.mainfiles)
            {
                temp++;
                if (temp == x)
                {

                    using (StreamReader reader = new StreamReader(file))
                    {
                        string v = Filename(file);
                        filePathSave=null;
                        filePathSave = file;

                        tblfilename.Text = v;
                        this.std1.Document = new TextDocument(reader);
                        var cSharptagger = new CSharpTagger(this.std1);
                        this.std1.TaggersRegistry.RegisterTagger(cSharptagger);

                        this.foldingTagger = new CSharpFoldingTagger(this.std1);
                        this.std1.TaggersRegistry.RegisterTagger(this.foldingTagger);

                        //this.foldingTagger = new BracketFoldingTagger(this.std);
                        //this.std.TaggersRegistry.RegisterTagger(this.foldingTagger);

                    }


                    //string obj01 = File.ReadAllText(filepath);
                    using (StreamReader reader = new StreamReader(file))
                    {
                        this.std.Document = new TextDocument(reader);


                        //SimpleToolTipTagger obj = new SimpleToolTipTagger();

                       
                        //String[] str2 = new string[] { "string", "public", "fileExtension", "name", "path", "connectionStrings", "configuration", "basicHttpBinding", "package", "dependentAssembly" , "assemblyBinding","Add" };
                        List<AppHighlight> str3 = new List<AppHighlight>();

                        List<string> tempstr3 = new List<String> ();
                       //List<AppCodeBlockDetails> appcb = new List<AppCodeBlockDetails>();
                       // appcb = new MigSourceCodeUpdate().AppCodeBlockDetailsl();
                        foreach(var str in App.appCodeBlockDetails)
                        {
                            AppHighlight apph = new AppHighlight();
                            apph.CodeBlock = str.CodeBlock;
                            apph.DataPoint = str.DataPoint;    
                            str3.Add(apph);
                            
                        }
                        App.highlightsList.AddRange(str3);
                        //str2l = tempstr3.Select(i => i.ToString()).ToList();
                        
                        if (App.highlightsList != null)
                        {
                            
                            foreach (var item in App.highlightsList)
                            {
                                tempstr3.Add(item.DataPoint);
                                var simpleToolTipTagger = new SimpleToolTipTagger1(this.std);
                                if (!this.std.TaggersRegistry.IsTaggerRegistered(simpleToolTipTagger))
                                {
                                    this.std.TaggersRegistry.RegisterTagger(simpleToolTipTagger);
                                }
                                //string c= item.CodeBlock.ToString();
                                //string d= item.DataPoint.ToString();
                                //if(c.Contains(d))
                                //{ 
                                var customHighlightsTagger = new CustomTextHighlightTagger(this.std, CustomTextHighlightTagger.CustomHighlightDefinition,item.CodeBlock);
                                //if (item.CodeBlock != null)
                                //{
                                //    customHighlightsTagger = new CustomTextHighlightTagger(this.std, CustomTextHighlightTagger.CustomHighlightDefinition, item.CodeBlock);                                    
                                //}
                               // var customHighlightsTagger1 = new CustomTextHighlightTagger(this.std, CustomTextHighlightTagger.CustomHighlightDefinition, item.DataPoint);
                                this.std.TaggersRegistry.RegisterTagger(customHighlightsTagger);

                                //}
                                simpleToolTipTagger.UpdateSearchWord(item.DataPoint,item.CodeBlock);
                            }
                            //____________________
                            str2 = tempstr3.Select(i => i.ToString()).ToArray();

                        }


                        // str2 = tempstr3.Select(i => i.ToString()).ToArray();
                        //for (var i = 0; i < str3.Count; ++i)
                        //    str3[i] = str2[i].ToString();




                        //********************************************************************************************************


                        var pythonTagger = new PythonTagger(this.std);
                        if (!this.std.TaggersRegistry.IsTaggerRegistered(pythonTagger))
                        {
                            this.std.TaggersRegistry.RegisterTagger(pythonTagger);
                        }

                        this.std.TextFormatDefinitions.AddLast(ClassificationTypes.NumberLiteral, new TextFormatDefinition(new SolidColorBrush(Colors.Lime)));
                        this.std.TextFormatDefinitions.AddLast(ClassificationTypes.Keyword, new TextFormatDefinition(new SolidColorBrush(Colors.Red)));

                        this.std.TextFormatDefinitions.AddLast(ClassificationTypes.Operator, new TextFormatDefinition(new SolidColorBrush(Colors.YellowGreen)));

                    }



                }

            }
        }
        ///_________________________________________________


        public string Filename(string filepath)
        {
            string[] nam = Regex.Split(filepath, @"\\");

            return nam[nam.Length - 1];
        }

        //private void Click_Back(object sender, EventArgs e)
        //{
        //    MigSourceCodeUpdate codeUpdate = new MigSourceCodeUpdate();
        //    codeUpdate.
        //    this.NavigationService.Navigate(codeUpdate);
        //}

        public void Filecount()
        {
            countfile = App.mainfiles.Count;
            tblfilecount.Text = countfile.ToString();

        }
        private void cbbDays_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

            int ind = 0;
            var tf = cmbFilesShow.SelectedValue;
            foreach (var f in cmbFilesShow.Items)
            {
                ind++;
                if (f == tf)
                {
                    filecountno = ind;
                    BtnDisplay();
                    Displaycode(ind);
                    return;
                }
            }
        }

        public void BtnDisplay()
        {
            if (filecountno == 1)
            {
                btnPrevious.IsEnabled = false;
            }

            if (filecountno < countfile)
            {
                btnbtnNextf.IsEnabled = true;
            }

            // next-----------------------
            if (filecountno == countfile)
            {
                btnbtnNextf.IsEnabled = false;
            }

            if (filecountno > 1)
            {
                btnPrevious.IsEnabled = true;
            }
        }


        //public string FilenameD(string filepath)
        //{
        //    string[] nam = Regex.Split(filepath, @"\\");

        //    return nam[nam.Length - 1];
        //}

        //public void Displaycode(int x)
        //{
        //    int temp = 0;
        //    foreach (var file in App.mainfiles)
        //    {
        //        temp++;
        //        if (temp == x)
        //        {

        //            using (StreamReader reader = new StreamReader(file))
        //            {
        //                //string v = Filename(filepath);
        //                //tblfilename.Text = filepath;
        //                this.std1.Document = new TextDocument(reader);
        //                var cSharptagger = new CSharpTagger(this.std1);
        //                this.std1.TaggersRegistry.RegisterTagger(cSharptagger);

        //                this.foldingTagger = new CSharpFoldingTagger(this.std1);
        //                this.std1.TaggersRegistry.RegisterTagger(this.foldingTagger);

        //                //this.foldingTagger = new BracketFoldingTagger(this.std);
        //                //this.std.TaggersRegistry.RegisterTagger(this.foldingTagger);

        //            }


        //            //string obj01 = File.ReadAllText(filepath);
        //            using (StreamReader reader = new StreamReader(file))
        //            {
        //                this.std.Document = new TextDocument(reader);


        //                SimpleToolTipTagger obj = new SimpleToolTipTagger();

        //                var simpleToolTipTagger = new SimpleToolTipTagger1(this.std);
        //                if (!this.std.TaggersRegistry.IsTaggerRegistered(simpleToolTipTagger))
        //                {
        //                    this.std.TaggersRegistry.RegisterTagger(simpleToolTipTagger);
        //                }
        //                String[] str2 = new string[] { "string", "public" };
        //                simpleToolTipTagger.UpdateSearchWord(str2);

        //                //********************************************************************************************************


        //                var pythonTagger = new PythonTagger(this.std);
        //                if (!this.std.TaggersRegistry.IsTaggerRegistered(pythonTagger))
        //                {
        //                    this.std.TaggersRegistry.RegisterTagger(pythonTagger);
        //                }

        //                this.std.TextFormatDefinitions.AddLast(ClassificationTypes.NumberLiteral, new TextFormatDefinition(new SolidColorBrush(Colors.Red)));
        //                this.std.TextFormatDefinitions.AddLast(ClassificationTypes.Keyword, new TextFormatDefinition(new SolidColorBrush(Colors.Lime)));

        //                this.std.TextFormatDefinitions.AddLast(ClassificationTypes.Operator, new TextFormatDefinition(new SolidColorBrush(Colors.YellowGreen)));

        //            }



        //        }

        //    }
        //}

        private void btnPrevious_Click(object sender, RoutedEventArgs e)
        {
            filecountno--;
            //if (filecountno == 1)
            //{
            //    btnPrevious.IsEnabled = false;
            //}

            //if (filecountno <countfile )
            //{
            //    btnbtnNextf.IsEnabled = true;
            //}
            //if(filecountno==countfile)
            //{

            //}
            cmbFilesShow.SelectedIndex = filecountno - 1;
            BtnDisplay();


            Displaycode(filecountno);
        }

        private void btnbtnNextf_Click(object sender, RoutedEventArgs e)
        {
            filecountno++;
            // if (filecountno == countfile)
            //{
            //     btnbtnNextf.IsEnabled = false;  
            //}

            // if (filecountno > 1)
            //{
            //     btnPrevious.IsEnabled = true;
            //}
            //string vd = "kghjghghukghjh";
            //cmbFilesShow.Text = vd;
            //filecountno++;
            //cmbFilesShow.ItemsSource = App.mainfiles;
            //cmbFilesShow.SelectedIndex = 0;
            cmbFilesShow.SelectedIndex = filecountno - 1;
            BtnDisplay();
            Displaycode(filecountno);
        }

        private void btn_cut_Click(object sender, RoutedEventArgs e)
        {
            var selectedText = this.std.Selection.GetSelectedText();
            //  ConvertedSchema.Commands.NavigateNextMatchCommand.Execute(selectedText);
            this.std.Commands.CutCommand.Execute(selectedText);
        }



        private void btn_copy_Click(object sender, RoutedEventArgs e)
        {
            var selectedText = this.std.Selection.GetSelectedText();
            //ConvertedSchema.Commands.NavigateNextMatchCommand.Execute(selectedText);
            this.std.Commands.CopyCommand.Execute(selectedText);
        }

        private void btn_paste_Click(object sender, RoutedEventArgs e)
        {
            var selectedText = this.std.Selection.GetSelectedText();
            //ConvertedSchema.Commands.NavigateNextMatchCommand.Execute(selectedText);
            this.std.Commands.PasteCommand.Execute(selectedText);



        }
        private void btn_selectAll_Click(object sender, RoutedEventArgs e)
        {
            var selectedText = this.std.Selection.GetSelectedText();
            //ConvertedSchema.Commands.NavigateNextMatchCommand.Execute(selectedText);
            this.std.Commands.SelectAllCommand.Execute(selectedText);
        }

        private void btn_comment_Click(object sender, RoutedEventArgs e)
        {
            var selectedText = this.std.Selection.GetSelectedText();
            string contxt = this.std.Document.CurrentSnapshot.GetText();
            if (selectedText != "" && !selectedText.StartsWith("\n"))
            {
                contxt = contxt.Replace(selectedText, $"\n{selectedText}");
                selectedText = "\n" + selectedText;
                //string commenttxt = selectedText.Replace("\n", "--\n");
                string commenttxt = Regex.Replace(selectedText, @"\r\n?|\n", "\n--");
                contxt = contxt.Replace(selectedText, commenttxt.Trim());
            }

            this.std.Document = new TextDocument(contxt.Trim());
            // ConvertedSchema.Commands.SelectAllCommand.Execute(contxt);
            //var CommentedCodeTagger = new CommentCodeTagger(this.ConvertedSchema, CommentCodeTagger.CustomHighlightDefinition, "CUSTOMERS");
            //this.ConvertedSchema.TaggersRegistry.RegisterTagger(CommentedCodeTagger);
        }




        private void btn_uncomment_Click(object sender, RoutedEventArgs e)
        {
            var selectedText = this.std.Selection.GetSelectedText();
            string contxt = this.std.Document.CurrentSnapshot.GetText();
            if (selectedText != "" && !selectedText.StartsWith("\n"))
            {
                string uncommenttxt = Regex.Replace(selectedText, @"--", "");
                contxt = contxt.Replace(selectedText, uncommenttxt.Trim());
            }
            this.std.Document = new TextDocument(contxt.Trim());
        }

        private void Click_Summary(object sender, RoutedEventArgs e)
        {
            try
            {
                
                //List<AppCodeBlockDetails> appcb = new List<AppCodeBlockDetails>();
                //appcb = new MigSourceCodeUpdate().AppCodeBlockDetailsl();
                App.AppD.Clear();

                              
                var dpc= (from i in App.appCodeBlockDetails group i by new { i.DataPoint } into j select new AppDatapointSummary { _DataPoint = j.Key.DataPoint,_Count= j.Count(),_Occurrence=j.Key.DataPoint.Distinct().Count() });
                foreach (var d in dpc)
                {
                    AppDatapointSummary appDatapointSummary = new AppDatapointSummary();
                    appDatapointSummary._DataPoint=d._DataPoint;
                    appDatapointSummary._Occurrence=d._Occurrence;  
                    appDatapointSummary._Count=d._Count;

                    appDatapointSummary._Status=d._Status;
                    App.AppD.Add(appDatapointSummary);
                    
                }
               
                var tt = App.AppD;

                MgEditSummary mgEditSummary = new MgEditSummary();
                mgEditSummary.ShowDialog();

            }
            catch
            {
                throw;
            }



        }

        private void Click_Save(object sender, EventArgs e)
        {
            try
            {
                this.std.SelectAll();
                var selectedText = this.std.Selection.GetSelectedText();
                //string filepath = @"C:\xaml\Main.java";
                if (File.Exists(filePathSave))
                {
                    File.Create(filePathSave).Close();
                    using (StreamWriter sw = File.AppendText(filePathSave))
                    {
                        sw.WriteLine(selectedText);
                    }
                    Button btn = sender as Button;
                    string str = btn.Content.ToString() + "File is Successfully Save";
                    MessageBox.Show(str);
                }
                else
                {
                    File.WriteAllText(filePathSave, string.Empty);
                    using (StreamWriter sw = File.AppendText(filePathSave))
                    {
                        sw.WriteLine(selectedText);
                    }
                    Button btn = sender as Button;
                    string str = btn.Content.ToString() + "File is Successfully Save";
                    MessageBox.Show(str);

                }
            }
            catch
            {
                throw;
            }
            


            //string fname = tblfilename.Text;
            //foreach (var file in App.mainfiles)
            //{
            //    if (file.Contains(fname))
            //    {

            //    }
            //}
            //var t = this.std.
            // string selectedText = this.std.SyntaxEditorElement.Selection.GetSelectedText();
            //string fileName = @"C:\xaml\Utility.cs";



        }

        private void Click_SaveNext(object sender, RoutedEventArgs e)
        {
            try
            {
                this.std.SelectAll();
                var selectedText = this.std.Selection.GetSelectedText();
                //string filepath = @"C:\xaml\Main.java";
                if (File.Exists(filePathSave))
                {
                    File.Create(filePathSave).Close();
                    using (StreamWriter sw = File.AppendText(filePathSave))
                    {
                        sw.WriteLine(selectedText);
                    }
                    if (filecountno == countfile)
                    {
                        btnbtnNextf.IsEnabled = false;
                    }
                    filecountno++;
                    Displaycode(filecountno);
                }
            }
            catch
            {
                throw;
            }
            

            Button btn = sender as Button;
            string str = btn.Content.ToString() + " button clicked";
            MessageBox.Show(str);
        }
        public class PythonTagger : WordTaggerBase
        {
            
            public static String[] Keywords = str2.Distinct().ToArray(); 
            //private static readonly string[] Keywords = new string[]
            //{
            //  // Keywords = str2.Select(i => i.ToString()).ToArray();

            ////  "string", "public","add","Add","fileExtension", "name","Connection","path", "connectionStrings", "configuration", "basicHttpBinding", "package", "dependentAssembly" , "assemblyBinding"


            ////"False", "None", "True", "and", "as", "assert","break", "class",
            ////"continue", "def", "del", "elif", "else", "except", "for", "from",
            ////"global", "if", "import", "in", "is", "lambda", "nonlocal", "not",
            ////"or", "pass", "raise", "finally", "return", "try", "while", "with", "yield"
            //};


            private static readonly string[] Comments = new string[]
            {
        "#","<!--"
            };

            private static readonly string[] Operators = new string[]
            {
        "+", "-",  "*", "/"
            };

            ////    public static readonly ClassificationType FruitsClassificationType = new ClassificationType("Fruits");

            //////    private static readonly string[] Fruits = new string[]
            //////    {
            //////"apple", "banana", "blue_berry", "cherry"
            //////    };

            private static readonly Dictionary<string, ClassificationType> WordsToClassificationType = new Dictionary<string, ClassificationType>();

            static PythonTagger()
            {
                WordsToClassificationType = new Dictionary<string, ClassificationType>();

                foreach (var keyword in Keywords)
                {
                    WordsToClassificationType.Add(keyword, ClassificationTypes.Keyword);

                }

                foreach (var preprocessor in Operators)
                {
                    WordsToClassificationType.Add(preprocessor, ClassificationTypes.Operator);
                }

                foreach (var comment in Comments)
                {
                    WordsToClassificationType.Add(comment, ClassificationTypes.Comment);
                }

                ////foreach (var comment in Fruits)
                ////{
                ////    WordsToClassificationType.Add(comment, FruitsClassificationType);
                ////}
            }

            public PythonTagger(Telerik.Windows.Controls.RadSyntaxEditor editor)
              : base(editor)
            {
            }

            protected override Dictionary<string, ClassificationType> GetWordsToClassificationTypes()
            {
                return PythonTagger.WordsToClassificationType;
            }

            protected override bool TryGetClassificationType(string word, out ClassificationType classificationType)
            {
                int number;

                if (int.TryParse(word, out number))
                {
                    classificationType = ClassificationTypes.NumberLiteral;
                    return true;
                }

                return base.TryGetClassificationType(word, out classificationType);
            }

            protected override void OnWordSplit(int wordCharType, string word)
            {
                if (wordCharType == 3 && word.Length > 1)
                {
                    this.AddWord(word, ClassificationTypes.Comment);
                }
            }

            protected override int GetCharType(char c)
            {
                if (c == '#')
                {
                    return 3;
                }

                if (c == '_')
                {
                    return 0;
                }

                if (char.IsWhiteSpace(c))
                {
                    return 1;
                }

                if (char.IsPunctuation(c) || char.IsSymbol(c))
                {
                    return 2;
                }

                return 0;
            }
        }

        public class AppHighlight
        {
            public string DataPoint { get; set; }
            public string CodeBlock { get; set; }
           
        }


    }
}
