package com.example.retrofit2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit2.model.CatFacts
import com.example.retrofit2.ui.theme.Retrofit2Theme
import com.example.retrofit2.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {

    private var fact = mutableStateOf(CatFacts())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            //https://catfact.ninja/fact
            Retrofit2Theme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { sendRequest() },
                    color = MaterialTheme.colorScheme.background
                ) {
                    sendRequest()
//                    val context = LocalContext.current
//                    var fact by remember{
//                        mutableStateOf(CatFacts())
//                    }
//                    val scope = rememberCoroutineScope()
//                    LaunchedEffect(key1 = true) {
//                        scope.launch(Dispatchers.IO) {
//                            val response = try{
//                                RetrofitInstance.api.getRandomFact()
//                            }catch (e:HttpException){
//                                Toast.makeText(context,"http error : ${e.message}",Toast.LENGTH_SHORT).show()
//                                return@launch
//                            }catch(e : IOException){
//                                Toast.makeText(context,"app error : ${e.message}",Toast.LENGTH_SHORT).show()
//
//                                return@launch
//                            }
//                            if(response.isSuccessful && response.body() != null){
//                                withContext(Dispatchers.Main){
//                                    fact = response.body()!!
//                                }
//                            }
//                        }
//                    }
                    MyUi(fact = fact)
                }
            }
        }
    }

    private fun sendRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            //앱이  종료될떄까지 계속 실행해주는 Scope
            //Dispather.IO는  IO작업(읽고 쓰기, 네트워크 요청 등)에 최적화된 스레드풀에서 코루틴 실행하도록 지시
            //IO작업은 메인스레드에서 실행하면 UI가 멈추거나 느려져서 별도의 스레드에서 처리

            val response = try {
                RetrofitInstance.api.getRandomFact()
                //응답은 객체의 api에서 -> api의 형식은 인터페이스 ->내부 메서드 getRandomFact동작
            } catch (e: HttpException) {
                Toast.makeText(applicationContext, "http error : ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "app error : ${e.message}", Toast.LENGTH_SHORT).show()

                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    fact.value = response.body()!!
                }
            }

        }
    }
}

@Composable
fun MyUi(fact: MutableState<CatFacts>, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Cat Facts : ", modifier.padding(bottom = 25.dp), fontSize = 26.sp)
        Text(
            text = fact.value.fact,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 40.sp
        ) //  첫번쨰 fact는 class, 두번째는 dataclass의 값
    }
}