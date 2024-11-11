package com.kikii.smarttsassignment.ui.feature.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kikii.smarttsassignment.R
import com.kikii.smarttsassignment.ui.components.textField.SmartTsOutlinedTextField

//@Composable
//fun LoginScreen(
//    onLoginSuccess: () -> Unit
//) {
//    var loginId by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//    ){
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = stringResource(id =R.string.login), style = MaterialTheme.typography.titleLarge)
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // 로그인 ID 입력 필드
//        SmartTsOutlinedTextField(
//            label = stringResource(id = R.string.id),
//            value = loginId,
//            onValueChange = { loginId = it },
//            observable = true,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // 비밀번호 입력 필드
//        SmartTsOutlinedTextField(
//            label = stringResource(id = R.string.password),
//            value = password,
//            onValueChange = { password = it },
//            observable = false,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // 로그인 버튼
//        Button(
//            onClick = {
//                // 로그인 로직 수행 (예시로 간단히 인증 시도)
//                if (loginId == "djDEMO" && password == "DEMO") { // 샘플 인증 정보
//                    onLoginSuccess() // 로그인 성공 시 콜백 호출
////                    errorMessage = "성공"
//                } else {
//                    errorMessage = "로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다."
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("로그인")
//        }
//
//            // 오류 메시지 표시
//            errorMessage?.let {
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(text = it, style = MaterialTheme.typography.bodyMedium)
//            }
//        }
//    }
//}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            SmartTsOutlinedTextField(
                label = stringResource(id = R.string.id),
                value = viewModel.loginId,
                onValueChange = viewModel::onLoginIdChange,
                observable = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            SmartTsOutlinedTextField(
                label = stringResource(id = R.string.password),
                value = viewModel.password,
                onValueChange = viewModel::onPasswordChange,
                observable = false,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.onLoginClick(onLoginSuccess) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("로그인")
            }

            viewModel.errorMessage?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(onLoginSuccess = {})
}